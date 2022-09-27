package vn.unicloud.umeepay.handler.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.payment.request.CreateTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.request.QueryTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.response.CreateTransactionResponse;
import vn.unicloud.umeepay.dtos.payment.response.QueryTransactionResponse;
import vn.unicloud.umeepay.repository.TransactionRepository;
import vn.unicloud.umeepay.service.PaymentService;

@Component
public class QueryTransactionHandler extends RequestHandler<QueryTransactionRequest, QueryTransactionResponse> {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public QueryTransactionResponse handle(QueryTransactionRequest request) {
        return paymentService.queryTransaction(request);
    }
}
