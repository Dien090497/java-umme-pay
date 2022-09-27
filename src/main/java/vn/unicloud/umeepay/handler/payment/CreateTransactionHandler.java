package vn.unicloud.umeepay.handler.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.payment.request.CreateTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.response.CreateTransactionResponse;
import vn.unicloud.umeepay.repository.TransactionRepository;
import vn.unicloud.umeepay.service.PaymentService;
import vn.unicloud.umeepay.service.TransactionService;

@Component
public class CreateTransactionHandler extends RequestHandler<CreateTransactionRequest, CreateTransactionResponse> {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public CreateTransactionResponse handle(CreateTransactionRequest request) {
        return paymentService.createTransaction(request);
    }
}
