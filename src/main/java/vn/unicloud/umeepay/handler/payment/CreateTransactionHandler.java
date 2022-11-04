package vn.unicloud.umeepay.handler.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.payment.request.CreateTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.response.CreateTransactionResponse;
import vn.unicloud.umeepay.repository.TransactionRepository;
import vn.unicloud.umeepay.service.PaymentService;

@Component
@RequiredArgsConstructor
public class CreateTransactionHandler extends RequestHandler<CreateTransactionRequest, CreateTransactionResponse> {

    private final PaymentService paymentService;

    private final TransactionRepository transactionRepository;

    @Override
    public CreateTransactionResponse handle(CreateTransactionRequest request) {
        return paymentService.createTransaction(request);
    }
}
