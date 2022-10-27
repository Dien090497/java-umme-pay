package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.dtos.payment.request.CancelTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.request.CreateTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.request.QueryTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.response.CancelTransactionResponse;
import vn.unicloud.umeepay.dtos.payment.response.CreateTransactionResponse;
import vn.unicloud.umeepay.dtos.payment.response.QueryTransactionResponse;
import vn.unicloud.umeepay.entity.Transaction;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.TransactionStatus;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.MerchantRepository;
import vn.unicloud.umeepay.repository.TransactionRepository;
import vn.unicloud.umeepay.utils.CommonUtils;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@Log4j2
@RequiredArgsConstructor
public class PaymentService {

    @Value("${umeepay.prefix}")
    private String prefix;

    @Value("${umeepay.timeout}")
    private int timeout;

    @Value("${umeepay.bin}")
    private String bin;

    @Value("${umeepay.actualAccount}")
    private String actualAccount;

    private final TransactionRepository transactionRepository;

    private final MerchantRepository merchantRepository;

    private final CallbackService callbackService;

    public CreateTransactionResponse createTransaction(CreateTransactionRequest request) {
        Merchant merchant = merchantRepository.findByCredentialId(request.getCredential().getId());
        if (merchant == null) {
            throw new InternalException(ResponseCode.MERCHANT_NOT_FOUND);
        }
        Transaction testTransaction = transactionRepository.findByRefTransactionId(request.getRefTransactionId());
        if (testTransaction != null) {
            throw new InternalException(ResponseCode.DUPLICATE_REFERENCE_TRANSACTION_ID);
        }
        String virtualAccount = CommonUtils.generateVirtualAccount(prefix);
        log.debug("Virtual account: {}", virtualAccount);
        String content = CommonUtils.getContent(request.getRefTransactionId());
        String qrCode = CommonUtils.generateQRCode(bin, virtualAccount, request.getAmount(), content);
        log.debug("qrCode: {}", qrCode);

        Transaction transaction = Transaction.builder()
            .amount(request.getAmount())
            .refTransactionId(request.getRefTransactionId())
            .virtualAccount(virtualAccount)
            .status(TransactionStatus.CREATED)
            .createDateTime(LocalDateTime.now())
//            .accountNo(merchant.getAccountNo())
            .description(content)
            .merchant(merchant)
            .timestamp(Instant.now().getEpochSecond())
            .timeout(request.getTimeout() == null ? 0 : request.getTimeout())
            .build();
        Transaction saved = transactionRepository.save(transaction);

        return CreateTransactionResponse.builder()
            .transactionId(saved.getId())
            .amount(saved.getAmount())
            .createdDateTime(saved.getCreateDateTime())
            .description(request.getDescription())
            .qrCodeString(qrCode)
            .timeout(request.getTimeout())
            .url("/" + saved.getId())
            .virtualAccount(virtualAccount)
            .build();
    }

    public QueryTransactionResponse queryTransaction(QueryTransactionRequest request) {
        Transaction transaction = transactionRepository.findById(request.getTransactionId()).orElseThrow(
            () -> {throw new InternalException(ResponseCode.INVALID_TRANSACTION_ID);}
        );
        return QueryTransactionResponse.builder()
            .refTransactionId(transaction.getRefTransactionId())
            .amount(transaction.getAmount())
            .status(transaction.getStatus())
            .build();
    }

    public CancelTransactionResponse cancelTransaction(CancelTransactionRequest request) {
        Transaction transaction = transactionRepository.findById(request.getTransactionId()).orElseThrow(
            () -> {throw new InternalException(ResponseCode.INVALID_TRANSACTION_ID);}
        );
        transaction.setStatus(TransactionStatus.CANCELED);
        transactionRepository.save(transaction);
        return new CancelTransactionResponse(true);
    }

}
