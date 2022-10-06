package vn.unicloud.umeepay.handler.payment.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.client.testapi.TestApiTransaction;
import vn.unicloud.umeepay.client.testapi.transaction.request.*;
import vn.unicloud.umeepay.client.testapi.transaction.response.*;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.payment.request.QueryTransactionRequest;

@Component
public class QueryTransactionClientHandler extends RequestHandler<QueryTransactionClientRequest, QueryTransactionClientResponse> {
    @Autowired
    TestApiTransaction testApiTransaction;
    @Override
    public QueryTransactionClientResponse handle(QueryTransactionClientRequest request) {
        return testApiTransaction.testTransactionClient(request.getUrl(), request, QueryTransactionRequest.class, QueryTransactionClientResponse.class);
    }
}
