package vn.unicloud.umeepay.handler.vietqr;

import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.vietqr.request.ConfirmTransactionRequest;
import vn.unicloud.umeepay.dtos.vietqr.response.ConfirmTransactionResponse;
import vn.unicloud.umeepay.service.VietQRService;

@Component
public class ConfirmTransactionHandler extends RequestHandler<ConfirmTransactionRequest, ConfirmTransactionResponse> {

    private final VietQRService vietQRService;

    public ConfirmTransactionHandler(VietQRService vietQRService) {
        this.vietQRService = vietQRService;
    }

    @Override
    public ConfirmTransactionResponse handle(ConfirmTransactionRequest request) {
        return vietQRService.confirmTransaction(request);
    }
}
