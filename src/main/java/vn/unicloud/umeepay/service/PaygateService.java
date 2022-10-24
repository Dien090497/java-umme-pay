package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.client.ThirdPartyClient;
import vn.unicloud.umeepay.client.request.NotifyRequest;
import vn.unicloud.umeepay.dtos.paygate.request.DepositCheckingRequest;
import vn.unicloud.umeepay.dtos.paygate.request.InquiryCheckingRequest;
import vn.unicloud.umeepay.dtos.paygate.request.NotifyTransactionRequest;
import vn.unicloud.umeepay.dtos.paygate.response.DepositCheckingResponse;
import vn.unicloud.umeepay.dtos.paygate.response.InquiryCheckingResponse;
import vn.unicloud.umeepay.dtos.paygate.response.NotifyTransactionResponse;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.Transaction;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.TransactionStatus;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.TransactionRepository;
import vn.unicloud.umeepay.utils.CommonUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Log4j2
@RequiredArgsConstructor
public class PaygateService {

    @Value("${umeepay.prefix}")
    private String prefix;

    @Value("${umeepay.timeout:60}")
    private int timeout;

    @Value("${umeepay.actualAccount}")
    private String actualAccount;

    private final PaymentService vietQRService;

    private final CallbackService callbackService;

    private final TransactionRepository transactionRepository;

    private final ThirdPartyClient thirdPartyClient;

    @SneakyThrows
    private Transaction getTransaction(String virtualAccount) {
        String prefix = CommonUtils.getPrefixByAccount(virtualAccount);
        if (prefix == null || !prefix.equals(this.prefix)) {
            log.error("Invalid prefix: {}", prefix);
            throw new InternalException(ResponseCode.INVALID_VIRTUAL_ACCOUNT);
        }
        Transaction transaction = transactionRepository.findByVirtualAccount(virtualAccount);
        if (transaction == null) {
            log.error("Invalid virtual account: {}", virtualAccount);
            throw new InternalException(ResponseCode.INVALID_VIRTUAL_ACCOUNT);
        }
        if (transaction.getTimeout() > 0 && CommonUtils.isExpired(transaction.getTimestamp() + transaction.getTimeout())) {
            log.error("Timeout");
            transaction.setStatus(TransactionStatus.TIMEOUT);
            transactionRepository.save(transaction);
            throw new InternalException(ResponseCode.TRANSACTION_TIMEOUT);
        }
        if (!transaction.getStatus().equals(TransactionStatus.CREATED)) {
            log.error("Invalid transaction status: {}", transaction.getStatus());
            throw new InternalException(ResponseCode.INVALID_TRANSACTION_STATE);
        }
        return transaction;
    }

    @SneakyThrows
    public InquiryCheckingResponse inquiry(InquiryCheckingRequest request) {
        Transaction transaction = this.getTransaction(request.getVirtualAccount());
        return new InquiryCheckingResponse(transaction.getMerchant().getProfile().getName(), transaction.getMerchant().getAccountId());
    }

    @SneakyThrows
    public DepositCheckingResponse depositChecking(DepositCheckingRequest request) {
        Transaction transaction = this.getTransaction(request.getVirtualAccount());
        if (!Objects.equals(transaction.getAmount(), request.getAmount())) {
            log.error("Amount did not equal, required = {}, receive = {}", transaction.getAmount(), request.getAmount());
            throw new InternalException(ResponseCode.INVALID_AMOUNT);
        }
        Merchant merchant = transaction.getMerchant();
        return new DepositCheckingResponse(merchant.getProfile().getName(), merchant.getAccountId(), transaction.getAmount(), true);
    }

    @SneakyThrows
//    @Transactional
    public NotifyTransactionResponse notifyTransaction(NotifyTransactionRequest request) {
        Transaction transaction = this.getTransaction(request.getVirtualAccount());

        transaction.setTxnNumber(request.getTxnNumber());
        transaction.setCallbackResponseCode(request.getStatusCode());
        transaction.setFromAccount(request.getFromAccount());
        transaction.setFromBin(request.getFromBin());

        if (!request.isSuccess()) {
            transaction.setStatus(TransactionStatus.FAIL);
            transactionRepository.save(transaction);
            return new NotifyTransactionResponse(true);
        }

        transaction.setDepositTime(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);

        Merchant merchant = transaction.getMerchant();

        if (merchant != null && StringUtils.isNoneBlank(merchant.getWebhookUrl())) {
            NotifyRequest notifyRequest = NotifyRequest.builder()
                .actualAccount(transaction.getAccountNo())
                .amount(transaction.getAmount())
                .fromAccount(transaction.getFromAccount())
                .fromBin(transaction.getFromBin())
                .refTransactionId(transaction.getRefTransactionId())
                .transactionId(transaction.getId())
                .success(transaction.getStatus().equals(TransactionStatus.SUCCESS))
                .transferDesc(transaction.getDescription())
                .txnNumber(transaction.getTxnNumber())
                .build();
            try {
                thirdPartyClient.notify(merchant.getWebhookUrl(), merchant.getWebhookApiKey(), notifyRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
