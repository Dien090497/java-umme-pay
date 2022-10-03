package vn.unicloud.umeepay.handler.payment.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
;
import vn.unicloud.umeepay.client.testapi.TestApiTransaction;
import vn.unicloud.umeepay.client.testapi.transaction.request.*;
import vn.unicloud.umeepay.client.testapi.transaction.response.*;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.payment.request.CancelTransactionRequest;

@Component
public class CancelTransactionClientHandler extends RequestHandler<CancelTransactionClientRequest, CancelTransactionClientResponse> {
    @Autowired
    TestApiTransaction testApiTransaction;
    @Override
    public CancelTransactionClientResponse handle(CancelTransactionClientRequest request) {
        return testApiTransaction.testTransactionClient(request.getUrl(), request, CancelTransactionRequest.class, CancelTransactionClientResponse.class);
    }
}
