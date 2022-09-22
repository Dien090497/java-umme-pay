package vn.unicloud.umeepay.service;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.dtos.paygate.request.DepositCheckingRequest;
import vn.unicloud.umeepay.dtos.paygate.request.InquiryCheckingRequest;
import vn.unicloud.umeepay.dtos.paygate.request.NotifyTransactionRequest;
import vn.unicloud.umeepay.dtos.paygate.response.DepositCheckingResponse;
import vn.unicloud.umeepay.dtos.paygate.response.InquiryCheckingResponse;
import vn.unicloud.umeepay.dtos.paygate.response.NotifyTransactionResponse;
import vn.unicloud.umeepay.entity.Transaction;
import vn.unicloud.umeepay.enums.CallbackStatus;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.TransactionStatus;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.model.CallbackMessage;
import vn.unicloud.umeepay.model.TransactionCallback;
import vn.unicloud.umeepay.repository.TransactionRepository;
import vn.unicloud.umeepay.utils.CommonUtils;

import java.util.Objects;

@Service
@Log4j2
public class PaygateService {

    @Value("${vietqr.prefix}")
    private String prefix;

    @Value("${vietqr.timeout:60}")
    private int timeout;

    @Value("${soap.message-description}")
    private String messageDescription;

    @Value("${soap.uri.inet}")
    private String soapUri;

    @Value("${soap.action.stm-withdrawal}")
    private String withdrawalAction;

    @Value("${vietqr.actualAccount}")
    private String actualAccount;

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
        return new InquiryCheckingResponse(transaction.getTerminalLocation(), actualAccount);
    }

    @SneakyThrows
    public DepositCheckingResponse depositChecking(DepositCheckingRequest request) {
        Transaction transaction = this.getTransaction(request.getVirtualAccount());
        if (!Objects.equals(transaction.getAmount(), request.getAmount())) {
            log.error("Amount did not equal, required = {}, receive = {}", transaction.getAmount(), request.getAmount());
            TransactionCallback callback = callbackService.getHashMap().get(transaction.getId().toString());
            transaction.setStatus(TransactionStatus.INVALID_AMOUNT);
            transactionRepository.save(transaction);
            if (callback != null && callback.getEventListener() != null) {
                log.debug("To be callback to listener");
                callback.getEventListener().onEvent(
                    CallbackMessage.builder()
                        .transactionId(transaction.getId().toString())
                        .status(CallbackStatus.INVALID_AMOUNT)
                        .build()
                );
            }
            throw new InternalException(ResponseCode.INVALID_AMOUNT);
        }
        return new DepositCheckingResponse(transaction.getTerminalLocation(), actualAccount, transaction.getAmount(), true);
    }

    @SneakyThrows
    @Transactional
    public NotifyTransactionResponse notifyTransaction(NotifyTransactionRequest request) {
        Transaction transaction = this.getTransaction(request.getVirtualAccount());

        transaction.setTraceId(request.getTraceId());
        transaction.setCallbackErrorCode(request.getStatusCode());
        transaction.setCallbackErrorDesc(request.getDesc());

        if (!request.isSuccess()) {
            transaction.setStatus(TransactionStatus.DEPOSIT_FAILED);
            transactionRepository.save(transaction);
            return new NotifyTransactionResponse(true);
        }

        transaction.setStatus(TransactionStatus.DEPOSITED);
        transactionRepository.save(transaction);

//        Thread thread = new Thread(new Runnable() {
//            @Autowired
//            private PaygateService paygateService;
//
//            @Override
//            public void run() {
        TransactionCallback callback = callbackService.getHashMap().get(transaction.getId().toString());
        if (callback == null) {
            log.warn("Callback to client was null");
            return new NotifyTransactionResponse(false);
        }

        String rtxn = this.callAccountingToTerminal(transaction.getTerminalId(), actualAccount, transaction.getAmount());
        if (rtxn == null) {
            log.error("Call STM accounting error");
            transaction.setStatus(TransactionStatus.ACCOUNTING_FAILED);
            transactionRepository.save(transaction);
            if (callback.getEventListener() != null) {
                log.debug("To be callback to listener");
                callback.getEventListener().onEvent(
                    CallbackMessage.builder()
                        .transactionId(transaction.getId().toString())
                        .approvedCode(request.getApproveCode())
                        .errorCode(Integer.parseInt(request.getStatusCode()))
                        .status(CallbackStatus.ACCOUNTING_FAILED)
                        .errorDesc(request.getDesc())
                        .traceId(request.getTraceId())
                        .build()
                );
            }
            return new NotifyTransactionResponse(false);
        }

        transaction.setStatus(TransactionStatus.DISPENSE);
        transactionRepository.save(transaction);

        if (callback.getEventListener() != null) {
            log.debug("To be callback to listener");
            callback.getEventListener().onEvent(
                CallbackMessage.builder()
                    .transactionId(transaction.getId().toString())
                    .approvedCode(request.getApproveCode())
                    .errorCode(Integer.parseInt(request.getStatusCode()))
                    .status(CallbackStatus.SUCCESS)
                    .errorDesc(request.getDesc())
                    .traceId(request.getTraceId())
                    .build()
            );
        }
//            }
//        });

//        thread.start();

        return new NotifyTransactionResponse(true);
    }

    public String callAccountingToTerminal(String terminalID, String accountNo, long amount) {
        log.info("accountNo: {}", accountNo);
//        var stmWithDrawal = new STMWithDrawal();
//        stmWithDrawal.setTerminalId(terminalID);
//        stmWithDrawal.setAccountNo(accountNo);
//        stmWithDrawal.setTransAmount(amount);
//        stmWithDrawal.setDescText(messageDescription);
//        try {
//            log.info("Soap call to : {}", soapUri);
//            log.info("Soap call withdrawal: body info: {}", stmWithDrawal);
//            STMWithDrawalResponse response = stmSoapClient.stmWithdrawal(soapUri, stmWithDrawal, withdrawalAction);
//            log.info("Soap call withdrawal response: {}", response);
//            if (response.isSuccess()){
//                log.info("Soap call withdrawal success");
//                return response.getWthRefNbr();
//            } else {
//                return null;
//            }
//        } catch (Exception e){
//            e.printStackTrace();
////            log.error("Soap call exception: {}", e.getMessage());
//        }
        return null;
    }

}
