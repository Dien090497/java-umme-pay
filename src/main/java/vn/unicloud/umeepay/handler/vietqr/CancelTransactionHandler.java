package vn.unicloud.umeepay.handler.vietqr;

import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.vietqr.request.CancelTransactionRequest;
import vn.unicloud.umeepay.dtos.vietqr.response.CancelTransactionResponse;
import vn.unicloud.umeepay.service.VietQRService;

@Component
public class CancelTransactionHandler extends RequestHandler<CancelTransactionRequest, CancelTransactionResponse> {

    private final VietQRService vietQRService;

    public CancelTransactionHandler(VietQRService vietQRService) {
        this.vietQRService = vietQRService;
    }

    @Override
    public CancelTransactionResponse handle(CancelTransactionRequest request) {
        return vietQRService.cancelTransaction(request);
    }
}
