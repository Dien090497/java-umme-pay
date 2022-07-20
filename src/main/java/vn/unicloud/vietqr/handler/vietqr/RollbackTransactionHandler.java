package vn.unicloud.vietqr.handler.vietqr;

import org.springframework.stereotype.Component;
import vn.unicloud.vietqr.core.RequestHandler;
import vn.unicloud.vietqr.dtos.vietqr.request.ConfirmTransactionRequest;
import vn.unicloud.vietqr.dtos.vietqr.request.RollbackTransactionRequest;
import vn.unicloud.vietqr.dtos.vietqr.response.ConfirmTransactionResponse;
import vn.unicloud.vietqr.dtos.vietqr.response.RollbackTransactionResponse;
import vn.unicloud.vietqr.service.VietQRService;

@Component
public class RollbackTransactionHandler extends RequestHandler<RollbackTransactionRequest, RollbackTransactionResponse> {

    private final VietQRService vietQRService;

    public RollbackTransactionHandler(VietQRService vietQRService) {
        this.vietQRService = vietQRService;
    }

    @Override
    public RollbackTransactionResponse handle(RollbackTransactionRequest request) {
        return vietQRService.rollbackTransaction(request);
    }
}
