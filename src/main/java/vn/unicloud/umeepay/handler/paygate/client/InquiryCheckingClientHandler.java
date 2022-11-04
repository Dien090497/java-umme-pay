package vn.unicloud.umeepay.handler.paygate.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.client.testapi.TestApiTransaction;
import vn.unicloud.umeepay.client.testapi.paygate.request.InquiryCheckingClientRequest;
import vn.unicloud.umeepay.client.testapi.paygate.response.InquiryCheckingClientResponse;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.paygate.request.InquiryCheckingRequest;

@Component
@RequiredArgsConstructor
public class InquiryCheckingClientHandler extends RequestHandler<InquiryCheckingClientRequest, InquiryCheckingClientResponse> {

    private final TestApiTransaction testApiTransaction;

    @Override
    public InquiryCheckingClientResponse handle(InquiryCheckingClientRequest request) {
        return testApiTransaction.testTransactionClient(request.getUrl(), request, InquiryCheckingRequest.class, InquiryCheckingClientResponse.class);
    }
}
