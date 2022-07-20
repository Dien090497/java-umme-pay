package vn.unicloud.vietqr.handler.vietqr;

import org.springframework.stereotype.Component;
import vn.unicloud.vietqr.core.RequestHandler;
import vn.unicloud.vietqr.dtos.vietqr.request.CheckTransactionRequest;
import vn.unicloud.vietqr.dtos.vietqr.request.ConfirmTransactionRequest;
import vn.unicloud.vietqr.dtos.vietqr.response.CheckTransactionResponse;
import vn.unicloud.vietqr.dtos.vietqr.response.ConfirmTransactionResponse;
import vn.unicloud.vietqr.service.VietQRService;

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
