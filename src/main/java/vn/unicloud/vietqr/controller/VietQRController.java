package vn.unicloud.vietqr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.vietqr.controller.interfaces.IVietQRController;
import vn.unicloud.vietqr.core.BaseController;
import vn.unicloud.vietqr.core.ResponseBase;
import vn.unicloud.vietqr.dtos.vietqr.request.*;
import vn.unicloud.vietqr.dtos.vietqr.response.*;

@RestController
public class VietQRController extends BaseController implements IVietQRController {

    @Override
    public ResponseEntity<ResponseBase<CreateTransactionResponse>> createTransaction(CreateTransactionRequest request) {
        return this.execute(request, CreateTransactionResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<CheckTransactionResponse>> checkTransaction(String transactionId) {
        CheckTransactionRequest request = new CheckTransactionRequest();
        request.setTransactionId(transactionId);
        return this.execute(request, CheckTransactionResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<CancelTransactionResponse>> cancelTransaction(String transactionId) {
        CancelTransactionRequest request = new CancelTransactionRequest();
        request.setTransactionId(transactionId);
        return this.execute(request, CancelTransactionResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<ConfirmTransactionResponse>> confirmTransaction(ConfirmTransactionRequest request) {
        return this.execute(request, ConfirmTransactionResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<RollbackTransactionResponse>> rollback(RollbackTransactionRequest request) {
        return this.execute(request, RollbackTransactionResponse.class);
    }
}
