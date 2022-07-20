package vn.unicloud.vietqr.handler.paygate;

import org.springframework.stereotype.Component;
import vn.unicloud.vietqr.core.RequestHandler;
import vn.unicloud.vietqr.dtos.paygate.request.DepositCheckingRequest;
import vn.unicloud.vietqr.dtos.paygate.request.NotifyTransactionRequest;
import vn.unicloud.vietqr.dtos.paygate.response.DepositCheckingResponse;
import vn.unicloud.vietqr.dtos.paygate.response.NotifyTransactionResponse;
import vn.unicloud.vietqr.service.PaygateService;

@Component
public class NotifyTransactionHandler extends RequestHandler<NotifyTransactionRequest, NotifyTransactionResponse> {

    private final PaygateService paygateService;

    public NotifyTransactionHandler(PaygateService paygateService) {
        this.paygateService = paygateService;
    }

    @Override
    public NotifyTransactionResponse handle(NotifyTransactionRequest request) {
        return paygateService.notifyTransaction(request);
    }
}
