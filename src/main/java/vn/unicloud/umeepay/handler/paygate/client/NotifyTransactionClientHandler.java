package vn.unicloud.umeepay.handler.paygate.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.client.testapi.TestApiTransaction;
import vn.unicloud.umeepay.client.testapi.paygate.request.NotifyTransactionClientRequest;
import vn.unicloud.umeepay.client.testapi.paygate.response.NotifyTransactionClientResponse;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.paygate.request.NotifyTransactionRequest;

@Component
@RequiredArgsConstructor
public class NotifyTransactionClientHandler extends RequestHandler<NotifyTransactionClientRequest, NotifyTransactionClientResponse> {

    private final TestApiTransaction testApiTransaction;

    @Override
    public NotifyTransactionClientResponse handle(NotifyTransactionClientRequest request) {
        return testApiTransaction.testTransactionClient(request.getUrl(), request, NotifyTransactionRequest.class, NotifyTransactionClientResponse.class);
    }
}
