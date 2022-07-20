package vn.unicloud.vietqr.handler.paygate;

import org.springframework.stereotype.Component;
import vn.unicloud.vietqr.core.RequestHandler;
import vn.unicloud.vietqr.dtos.paygate.request.InquiryCheckingRequest;
import vn.unicloud.vietqr.dtos.paygate.response.InquiryCheckingResponse;
import vn.unicloud.vietqr.dtos.vietqr.request.CheckTransactionRequest;
import vn.unicloud.vietqr.dtos.vietqr.response.CheckTransactionResponse;
import vn.unicloud.vietqr.service.PaygateService;
import vn.unicloud.vietqr.service.VietQRService;

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
