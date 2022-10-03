package vn.unicloud.umeepay.handler.paygate.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.client.testapi.TestApiTransaction;
import vn.unicloud.umeepay.client.testapi.paygate.request.InquiryCheckingClientRequest;
import vn.unicloud.umeepay.client.testapi.paygate.response.InquiryCheckingClientResponse;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.paygate.request.InquiryCheckingRequest;

@Component
public class InquiryCheckingClientHandler extends RequestHandler<InquiryCheckingClientRequest, InquiryCheckingClientResponse> {
    @Autowired
    private TestApiTransaction testApiTransaction;

    @Override
    public InquiryCheckingClientResponse handle(InquiryCheckingClientRequest request) {
        return testApiTransaction.testTransactionClient(request.getUrl(), request, InquiryCheckingRequest.class, InquiryCheckingClientResponse.class);
    }
}
