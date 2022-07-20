package vn.unicloud.vietqr.handler.vietqr;

import org.springframework.stereotype.Component;
import vn.unicloud.vietqr.core.RequestHandler;
import vn.unicloud.vietqr.dtos.vietqr.request.CancelTransactionRequest;
import vn.unicloud.vietqr.dtos.vietqr.request.CheckTransactionRequest;
import vn.unicloud.vietqr.dtos.vietqr.response.CancelTransactionResponse;
import vn.unicloud.vietqr.dtos.vietqr.response.CheckTransactionResponse;
import vn.unicloud.vietqr.service.VietQRService;

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
