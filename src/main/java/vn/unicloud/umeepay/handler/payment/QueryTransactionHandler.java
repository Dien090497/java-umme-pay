package vn.unicloud.umeepay.handler.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.payment.request.QueryTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.response.QueryTransactionResponse;
import vn.unicloud.umeepay.repository.TransactionRepository;
import vn.unicloud.umeepay.service.PaymentService;

@Component
@RequiredArgsConstructor
public class QueryTransactionHandler extends RequestHandler<QueryTransactionRequest, QueryTransactionResponse> {

    private final PaymentService paymentService;

    private final TransactionRepository transactionRepository;

    @Override
    public QueryTransactionResponse handle(QueryTransactionRequest request) {
        return paymentService.queryTransaction(request);
    }
}
