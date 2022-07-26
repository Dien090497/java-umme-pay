package vn.unicloud.vietqr.handler.vietqr;

import org.springframework.stereotype.Component;
import vn.unicloud.vietqr.core.RequestHandler;
import vn.unicloud.vietqr.dtos.vietqr.request.CreateTransactionRequest;
import vn.unicloud.vietqr.dtos.vietqr.response.CreateTransactionResponse;
import vn.unicloud.vietqr.service.VietQRService;

@Component
public class CreateTransactionHandler extends RequestHandler<CreateTransactionRequest, CreateTransactionResponse> {

    private final VietQRService vietQRService;

    public CreateTransactionHandler(VietQRService vietQRService) {
        this.vietQRService = vietQRService;
    }

    @Override
    public CreateTransactionResponse handle(CreateTransactionRequest request) {
        return vietQRService.createTransaction(request);
    }
}
