package vn.unicloud.umeepay.handler.vietqr;

import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.vietqr.request.CreateTransactionRequest;
import vn.unicloud.umeepay.dtos.vietqr.response.CreateTransactionResponse;
import vn.unicloud.umeepay.service.VietQRService;

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
