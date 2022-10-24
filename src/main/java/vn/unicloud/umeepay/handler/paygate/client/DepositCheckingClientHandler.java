package vn.unicloud.umeepay.handler.paygate.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.client.testapi.TestApiTransaction;
import vn.unicloud.umeepay.client.testapi.paygate.request.DepositCheckingClientRequest;
import vn.unicloud.umeepay.client.testapi.paygate.response.DepositCheckingClientResponse;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.paygate.request.DepositCheckingRequest;

@Component
public class DepositCheckingClientHandler extends RequestHandler<DepositCheckingClientRequest, DepositCheckingClientResponse> {

    @Autowired
    private TestApiTransaction testApiTransaction;
    @Override
    public DepositCheckingClientResponse handle(DepositCheckingClientRequest request) {
        return testApiTransaction.testTransactionClient(request.getUrl(), request, DepositCheckingRequest.class, DepositCheckingClientResponse.class);
    }
}
