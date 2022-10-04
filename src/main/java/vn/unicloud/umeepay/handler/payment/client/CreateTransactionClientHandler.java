package vn.unicloud.umeepay.handler.payment.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
;
import vn.unicloud.umeepay.client.testapi.transaction.TestApiTransaction;
import vn.unicloud.umeepay.client.testapi.transaction.request.*;
import vn.unicloud.umeepay.client.testapi.transaction.response.*;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.payment.request.CreateTransactionRequest;

@Component
public class CreateTransactionClientHandler extends RequestHandler<CreateTransactionClientRequest, CreateTransactionClientResponse> {

    @Autowired
    TestApiTransaction testApiTransaction;
    @Override
    public CreateTransactionClientResponse handle(CreateTransactionClientRequest request) {
        return testApiTransaction.testTransactionClient(request.getUrl(), request, CreateTransactionRequest.class, CreateTransactionClientResponse.class);
    }
}
