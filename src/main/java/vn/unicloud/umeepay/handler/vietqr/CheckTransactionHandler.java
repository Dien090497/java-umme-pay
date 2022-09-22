package vn.unicloud.umeepay.handler.vietqr;

import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.vietqr.request.CheckTransactionRequest;
import vn.unicloud.umeepay.dtos.vietqr.response.CheckTransactionResponse;
import vn.unicloud.umeepay.service.VietQRService;

@Component
public class CheckTransactionHandler extends RequestHandler<CheckTransactionRequest, CheckTransactionResponse> {

    private final VietQRService vietQRService;

    public CheckTransactionHandler(VietQRService vietQRService) {
        this.vietQRService = vietQRService;
    }

    @Override
    public CheckTransactionResponse handle(CheckTransactionRequest request) {
        return vietQRService.checkTransaction(request);
    }
}
