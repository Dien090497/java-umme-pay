package vn.unicloud.umeepay.handler.paygate.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.client.testapi.TestApiTransaction;
import vn.unicloud.umeepay.client.testapi.paygate.request.DepositCheckingClientRequest;
import vn.unicloud.umeepay.client.testapi.paygate.response.DepositCheckingClientResponse;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.paygate.request.DepositCheckingRequest;

@Component
@RequiredArgsConstructor
public class DepositCheckingClientHandler extends RequestHandler<DepositCheckingClientRequest, DepositCheckingClientResponse> {

    private final TestApiTransaction testApiTransaction;

    @Override
    public DepositCheckingClientResponse handle(DepositCheckingClientRequest request) {
        return testApiTransaction.testTransactionClient(request.getUrl(), request, DepositCheckingRequest.class, DepositCheckingClientResponse.class);
    }
}
