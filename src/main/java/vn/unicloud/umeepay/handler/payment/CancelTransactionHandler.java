package vn.unicloud.umeepay.handler.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.payment.request.CancelTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.response.CancelTransactionResponse;
import vn.unicloud.umeepay.repository.TransactionRepository;
import vn.unicloud.umeepay.service.PaymentService;

@Component
@RequiredArgsConstructor
public class CancelTransactionHandler extends RequestHandler<CancelTransactionRequest, CancelTransactionResponse> {

    private final PaymentService paymentService;

    private final TransactionRepository transactionRepository;

    @Override
    public CancelTransactionResponse handle(CancelTransactionRequest request) {
        return paymentService.cancelTransaction(request);
    }
}
