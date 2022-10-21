package vn.unicloud.umeepay.handler.paygate.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.client.testapi.TestApiTransaction;
import vn.unicloud.umeepay.client.testapi.paygate.request.NotifyTransactionClientRequest;
import vn.unicloud.umeepay.client.testapi.paygate.response.NotifyTransactionClientResponse;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.paygate.request.NotifyTransactionRequest;

@Component
public class NotifyTransactionClientHandler extends RequestHandler<NotifyTransactionClientRequest, NotifyTransactionClientResponse> {

    @Autowired
    private TestApiTransaction testApiTransaction;

    @Override
    public NotifyTransactionClientResponse handle(NotifyTransactionClientRequest request) {
        return testApiTransaction.testTransactionClient(request.getUrl(), request, NotifyTransactionRequest.class, NotifyTransactionClientResponse.class);
    }
}
