package vn.unicloud.umeepay.handler.paygate;

import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.paygate.request.NotifyTransactionRequest;
import vn.unicloud.umeepay.dtos.paygate.response.NotifyTransactionResponse;
import vn.unicloud.umeepay.service.PaygateService;

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
