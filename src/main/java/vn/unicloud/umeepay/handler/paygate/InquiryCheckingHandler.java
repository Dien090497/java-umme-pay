package vn.unicloud.umeepay.handler.paygate;

import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.paygate.request.InquiryCheckingRequest;
import vn.unicloud.umeepay.dtos.paygate.response.InquiryCheckingResponse;
import vn.unicloud.umeepay.service.PaygateService;

@Component
public class InquiryCheckingHandler extends RequestHandler<InquiryCheckingRequest, InquiryCheckingResponse> {

    private final PaygateService paygateService;

    public InquiryCheckingHandler(PaygateService paygateService) {
        this.paygateService = paygateService;
    }

    @Override
    public InquiryCheckingResponse handle(InquiryCheckingRequest request) {
        return paygateService.inquiry(request);
    }
}
