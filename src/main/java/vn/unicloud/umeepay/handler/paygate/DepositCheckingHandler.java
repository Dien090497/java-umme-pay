package vn.unicloud.umeepay.handler.paygate;

import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.paygate.request.DepositCheckingRequest;
import vn.unicloud.umeepay.dtos.paygate.response.DepositCheckingResponse;
import vn.unicloud.umeepay.service.PaygateService;

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
