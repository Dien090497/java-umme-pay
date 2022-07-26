package vn.unicloud.vietqr.handler.paygate;

import org.springframework.stereotype.Component;
import vn.unicloud.vietqr.core.RequestHandler;
import vn.unicloud.vietqr.dtos.paygate.request.NotifyTransactionRequest;
import vn.unicloud.vietqr.dtos.paygate.request.TestAccountingRequest;
import vn.unicloud.vietqr.dtos.paygate.response.NotifyTransactionResponse;
import vn.unicloud.vietqr.dtos.paygate.response.TestAccountingResponse;
import vn.unicloud.vietqr.service.PaygateService;

@Component
public class TestAccountingHandler extends RequestHandler<TestAccountingRequest, TestAccountingResponse> {

    private final PaygateService paygateService;

    public TestAccountingHandler(PaygateService paygateService) {
        this.paygateService = paygateService;
    }

    @Override
    public TestAccountingResponse handle(TestAccountingRequest request) {
        return new TestAccountingResponse(paygateService.callAccountingToTerminal(request.getTerminalId(), request.getAccountNo(), request.getAmount()));
    }
}
