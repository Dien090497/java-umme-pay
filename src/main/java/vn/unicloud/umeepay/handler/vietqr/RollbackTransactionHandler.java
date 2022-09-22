package vn.unicloud.umeepay.handler.vietqr;

import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.vietqr.request.RollbackTransactionRequest;
import vn.unicloud.umeepay.dtos.vietqr.response.RollbackTransactionResponse;
import vn.unicloud.umeepay.service.VietQRService;

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
