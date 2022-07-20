package vn.unicloud.vietqr.handler.vietqr;

import org.springframework.stereotype.Component;
import vn.unicloud.vietqr.core.RequestHandler;
import vn.unicloud.vietqr.dtos.vietqr.request.CheckTransactionRequest;
import vn.unicloud.vietqr.dtos.vietqr.request.CreateTransactionRequest;
import vn.unicloud.vietqr.dtos.vietqr.response.CheckTransactionResponse;
import vn.unicloud.vietqr.dtos.vietqr.response.CreateTransactionResponse;
import vn.unicloud.vietqr.service.VietQRService;

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
