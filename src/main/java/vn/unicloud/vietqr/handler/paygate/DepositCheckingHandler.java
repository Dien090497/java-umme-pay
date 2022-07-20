package vn.unicloud.vietqr.handler.paygate;

import org.springframework.stereotype.Component;
import vn.unicloud.vietqr.core.RequestHandler;
import vn.unicloud.vietqr.dtos.paygate.request.DepositCheckingRequest;
import vn.unicloud.vietqr.dtos.paygate.request.InquiryCheckingRequest;
import vn.unicloud.vietqr.dtos.paygate.response.DepositCheckingResponse;
import vn.unicloud.vietqr.dtos.paygate.response.InquiryCheckingResponse;
import vn.unicloud.vietqr.service.PaygateService;

@Component
public class DepositCheckingHandler extends RequestHandler<DepositCheckingRequest, DepositCheckingResponse> {

    private final PaygateService paygateService;

    public DepositCheckingHandler(PaygateService paygateService) {
        this.paygateService = paygateService;
    }

    @Override
    public DepositCheckingResponse handle(DepositCheckingRequest request) {
        return paygateService.depositChecking(request);
    }
}
