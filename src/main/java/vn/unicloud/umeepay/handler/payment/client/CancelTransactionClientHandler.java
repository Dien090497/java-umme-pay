package vn.unicloud.umeepay.handler.payment.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
;
import vn.unicloud.umeepay.client.testapi.transaction.TestApiTransaction;
import vn.unicloud.umeepay.client.testapi.transaction.request.*;
import vn.unicloud.umeepay.client.testapi.transaction.response.*;
import vn.unicloud.umeepay.core.RequestHandler;

@Component
public class CancelTransactionClientHandler extends RequestHandler<CancelTransactionClientRequest, CancelTransactionClientResponse> {
    @Autowired
    TestApiTransaction testApiTransaction;

    private final String url = "http://localhost:1124/api/payment/v1/cancel";

    @Override
    public CancelTransactionClientResponse handle(CancelTransactionClientRequest request) {
        return testApiTransaction.testTransactionClient(url,request, CancelTransactionClientResponse.class);
    }
}
