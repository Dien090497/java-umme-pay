package vn.unicloud.vietqr.service;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.vietqr.dtos.paygate.request.DepositCheckingRequest;
import vn.unicloud.vietqr.dtos.paygate.request.InquiryCheckingRequest;
import vn.unicloud.vietqr.dtos.paygate.request.NotifyTransactionRequest;
import vn.unicloud.vietqr.dtos.paygate.response.DepositCheckingResponse;
import vn.unicloud.vietqr.dtos.paygate.response.InquiryCheckingResponse;
import vn.unicloud.vietqr.dtos.paygate.response.NotifyTransactionResponse;
import vn.unicloud.vietqr.entity.Transaction;
import vn.unicloud.vietqr.enums.CallbackStatus;
import vn.unicloud.vietqr.enums.ResponseCode;
import vn.unicloud.vietqr.enums.TransactionStatus;
import vn.unicloud.vietqr.exception.InternalException;
import vn.unicloud.vietqr.model.CallbackMessage;
import vn.unicloud.vietqr.model.TransactionCallback;
import vn.unicloud.vietqr.repository.TransactionRepository;
import vn.unicloud.vietqr.utils.CommonUtils;

import java.util.Objects;

@Service
@Log4j2
public class PaygateService {

    @Value("${vietqr.prefix}")
    private String prefix;

    @Value("${vietqr.timeout:60}")
    private int timeout;

    @Autowired
    private VietQRService vietQRService;

    @Autowired
    private CallbackService callbackService;

    @Autowired
    private TransactionRepository transactionRepository;

    @SneakyThrows
    private Transaction getTransaction(String virtualAccount) {
        String prefix = CommonUtils.getPrefixByAccount(virtualAccount);
        if (prefix == null || !prefix.equals(this.prefix)) {
            log.error("Invalid prefix: {}", prefix);
            throw new InternalException(ResponseCode.INVALID_VIRTUAL_ACCOUNT);
        }
        Transaction transaction = vietQRService.findTransactionByVirtualAccount(virtualAccount);
        if (transaction == null) {
            log.error("Invalid virtual account: {}", virtualAccount);
            throw new InternalException(ResponseCode.INVALID_VIRTUAL_ACCOUNT);
        }
        if (CommonUtils.isExpired(transaction.getTimestamp() + timeout * 1000L)) {
            log.error("Timeout");
            transaction.setStatus(TransactionStatus.TIMEOUT);
            transactionRepository.save(transaction);
            throw new InternalException(ResponseCode.VIETQR_TIMEOUT);
        }
        if (!transaction.getStatus().equals(TransactionStatus.WAITING)) {
            log.error("Invalid transaction status: {}", transaction.getStatus());
            throw new InternalException(ResponseCode.INVALID_TRANSACTION_STATE);
        }
        return transaction;
    }

    @SneakyThrows
    public InquiryCheckingResponse inquiry(InquiryCheckingRequest request) {
        Transaction transaction = this.getTransaction(request.getVirtualAccount());
        return new InquiryCheckingResponse(transaction.getTerminalLocation());
    }

    @SneakyThrows
    public DepositCheckingResponse depositChecking(DepositCheckingRequest request) {
        Transaction transaction = this.getTransaction(request.getVirtualAccount());
        if (!Objects.equals(transaction.getAmount(), request.getAmount())) {
            log.error("Amount did not equal, required = {}, receive = {}", transaction.getAmount(), request.getAmount());
            throw new InternalException(ResponseCode.INVALID_AMOUNT);
        }
        return new DepositCheckingResponse(transaction.getTerminalLocation(), transaction.getAmount(), true);
    }

    @SneakyThrows
    @Transactional
    public NotifyTransactionResponse notifyTransaction(NotifyTransactionRequest request) {
        Transaction transaction = this.getTransaction(request.getVirtualAccount());

        if (request.isSuccess()) {
            TransactionCallback callback = callbackService.getHashMap().get(transaction.getId());
            if (callback == null) {
                log.warn("Callback to client was null");
                return new NotifyTransactionResponse(false);
            }
            transaction.setCallbackErrorCode(request.getStatusCode());
            transaction.setCallbackErrorDesc(request.getDesc());
            transaction.setStatus(TransactionStatus.DISPENSE);
            transaction.setTraceId(request.getTraceId());
            transactionRepository.save(transaction);

            if (callback.getEventListener() != null) {
                log.debug("To be callback to listener");
                callback.getEventListener().onEvent(
                    CallbackMessage.builder()
                        .transactionId(transaction.getId())
                        .approvedCode(request.getApproveCode())
                        .errorCode(Integer.parseInt(request.getStatusCode()))
                        .status(CallbackStatus.SUCCESS)
                        .errorDesc(request.getDesc())
                        .traceId(request.getTraceId())
                        .build()
                );
            }
        }

        return new NotifyTransactionResponse(true);
    }

}
