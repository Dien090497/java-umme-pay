package vn.unicloud.vietqr.service;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.unicloud.vietqr.dtos.model.DispensedNotes;
import vn.unicloud.vietqr.dtos.vietqr.request.*;
import vn.unicloud.vietqr.dtos.vietqr.response.*;
import vn.unicloud.vietqr.entity.Transaction;
import vn.unicloud.vietqr.enums.CallbackStatus;
import vn.unicloud.vietqr.enums.ResponseCode;
import vn.unicloud.vietqr.enums.TransactionStatus;
import vn.unicloud.vietqr.exception.InternalException;
import vn.unicloud.vietqr.model.CallbackMessage;
import vn.unicloud.vietqr.model.TransactionCallback;
import vn.unicloud.vietqr.repository.TransactionRepository;
import vn.unicloud.vietqr.utils.CommonUtils;

import java.time.LocalDateTime;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Log4j2
public class VietQRService {

    @Value("${vietqr.prefix}")
    private String prefix;

    @Value("${vietqr.timeout}")
    private int timeout;

    @Value("${vietqr.bin}")
    private String bin;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CallbackService callbackService;

    public CreateTransactionResponse createTransaction(CreateTransactionRequest request) {
        String virtualAccount = CommonUtils.generateVirtualAccount(prefix);
        log.debug("Virtual account: {}", virtualAccount);
        String normalizeTerminalLocation = CommonUtils.normalizeTerminalLocation(request.getTerminalLocation());
        log.debug("Normalize terminal location to: {}", normalizeTerminalLocation);
        String qrCode = CommonUtils.generateQRCode(bin, virtualAccount, request.getAmount());
        log.debug("qrCode: {}", qrCode);

        Transaction transaction = Transaction.builder()
            .amount(request.getAmount())
            .bin(bin)
            .status(TransactionStatus.CREATED)
            .terminalId(request.getTerminalId())
            .isPrintReceipt(request.isPrintReceipt())
            .terminalLocation(normalizeTerminalLocation)
            .virtualAccount(virtualAccount)
            .createDate(LocalDateTime.now())
            .timestamp(System.currentTimeMillis())
            .build();
        Transaction saved = transactionRepository.save(transaction);

        return new CreateTransactionResponse(saved.getId(), timeout, qrCode, saved.getTerminalLocation());
    }

    @SneakyThrows
    public CheckTransactionResponse checkTransaction(CheckTransactionRequest request) {
        Transaction transaction = transactionRepository.findById(request.getTransactionId()).orElse(null);
        if (transaction == null) {
            log.error("Invalid Transaction Id");
            throw new InternalException(ResponseCode.INVALID_TRANSACTION_ID);
        }
        if (CommonUtils.isExpired(transaction.getTimestamp() + timeout * 1000L)) {
            log.error("Timeout");
            transaction.setStatus(TransactionStatus.TIMEOUT);
            transactionRepository.save(transaction);
            throw new InternalException(ResponseCode.VIETQR_TIMEOUT);
        }
        transaction.setStatus(TransactionStatus.WAITING);
        transactionRepository.save(transaction);

        TransactionCallback callback = new TransactionCallback();
        AtomicReference<CallbackMessage> callbackMessageWrapper = new AtomicReference<>(null);
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        callback.registerEvent(message -> {
            log.debug("Callback message: {}", message);
            callbackMessageWrapper.set(message);
            semaphore.release();
        });
        callbackService.getHashMap().put(transaction.getId(), callback);
        log.info("Client waiting for payment...");
        if (semaphore.tryAcquire(timeout * 1000L, TimeUnit.MILLISECONDS)) {
            log.warn("Callback signaled");
        }
        callback.deregisterEvent();
        CallbackMessage message = callbackMessageWrapper.get();
        if (message == null) {
            log.warn("Callback timeout");
            throw new InternalException(ResponseCode.VIETQR_TIMEOUT);
        }
        if (message.getErrorCode() == ResponseCode.SUCCESS.getCode()) {
            log.info("Transaction successful: {}", message.getTraceId());
            return new CheckTransactionResponse(message.getTransactionId(), true, message.getTraceId());
        }
        log.info("Transaction failed: {}", message.getTraceId());
        return new CheckTransactionResponse(message.getTransactionId(), false, message.getTraceId());
    }

    public Transaction findTransactionByVirtualAccount(String virtualAccount) {
        return transactionRepository.findFirstByVirtualAccount(virtualAccount);
    }

    public ConfirmTransactionResponse confirmTransaction(ConfirmTransactionRequest request) {
        Transaction transaction = transactionRepository.findById(request.getTransactionId()).orElse(null);
        if (transaction == null) {
            log.error("Invalid transaction");
            throw new InternalException(ResponseCode.INVALID_TRANSACTION_ID);
        }
        if (!transaction.getStatus().equals(TransactionStatus.DISPENSE)) {
            log.error("Invalid transaction state: {}", transaction.getStatus());
            throw new InternalException(ResponseCode.INVALID_TRANSACTION_STATE);
        }
        transaction.setStatus(TransactionStatus.SUCCESS);
        transaction.setDispensedNotes(
            DispensedNotes.builder()
                .note50(request.getNumNote50())
                .note100(request.getNumNote100())
                .note200(request.getNumNote200())
                .note500(request.getNumNote500())
                .build()
        );
        transactionRepository.save(transaction);
        return new ConfirmTransactionResponse(true);
    }

    public RollbackTransactionResponse rollbackTransaction(RollbackTransactionRequest request) {
        Transaction transaction = transactionRepository.findById(request.getTransactionId()).orElse(null);
        if (transaction == null) {
            log.error("Invalid transaction");
            throw new InternalException(ResponseCode.INVALID_TRANSACTION_ID);
        }
        if (!transaction.getStatus().equals(TransactionStatus.DISPENSE)) {
            log.error("Invalid transaction state: {}", transaction.getStatus());
            throw new InternalException(ResponseCode.INVALID_TRANSACTION_STATE);
        }
        transaction.setStatus(TransactionStatus.DISPENSE_FAIL);
        transaction.setDispensedErrorDesc(request.getReasonDesc());
        transactionRepository.save(transaction);
        return new RollbackTransactionResponse(true);
    }

    public CancelTransactionResponse cancelTransaction(CancelTransactionRequest request) {
        Transaction transaction = transactionRepository.findById(request.getTransactionId()).orElse(null);
        if (transaction == null) {
            log.error("Invalid transaction");
            throw new InternalException(ResponseCode.INVALID_TRANSACTION_ID);
        }
        if (transaction.getStatus().equals(TransactionStatus.SUCCESS)) {
            log.error("Invalid transaction state: {}", transaction.getStatus());
            throw new InternalException(ResponseCode.INVALID_TRANSACTION_STATE);
        }

        transaction.setStatus(TransactionStatus.CANCELED);
        transactionRepository.save(transaction);

        TransactionCallback callback = callbackService.getHashMap().get(transaction.getId());
        if (callback != null && callback.getEventListener() != null) {
            callback.getEventListener().onEvent(
                CallbackMessage.builder()
                    .transactionId(transaction.getId())
                    .status(CallbackStatus.CANCEL)
                    .build()
            );
        }

        return new CancelTransactionResponse(true);
    }
}
