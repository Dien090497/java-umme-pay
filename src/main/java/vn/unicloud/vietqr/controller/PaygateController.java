package vn.unicloud.vietqr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.vietqr.controller.interfaces.IPaygateController;
import vn.unicloud.vietqr.core.BaseController;
import vn.unicloud.vietqr.core.ResponseBase;
import vn.unicloud.vietqr.dtos.paygate.request.DepositCheckingRequest;
import vn.unicloud.vietqr.dtos.paygate.request.InquiryCheckingRequest;
import vn.unicloud.vietqr.dtos.paygate.request.NotifyTransactionRequest;
import vn.unicloud.vietqr.dtos.paygate.response.DepositCheckingResponse;
import vn.unicloud.vietqr.dtos.paygate.response.InquiryCheckingResponse;
import vn.unicloud.vietqr.dtos.paygate.response.NotifyTransactionResponse;

@RestController
public class PaygateController extends BaseController implements IPaygateController {

    @Override
    public ResponseEntity<ResponseBase<InquiryCheckingResponse>> inquiryChecking(String virtualAccount) {
        InquiryCheckingRequest request = new InquiryCheckingRequest();
        request.setVirtualAccount(virtualAccount);
        return this.execute(request, InquiryCheckingResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<DepositCheckingResponse>> depositChecking(String virtualAccount, Long amount) {
        DepositCheckingRequest request = new DepositCheckingRequest();
        request.setVirtualAccount(virtualAccount);
        request.setAmount(amount);
        return this.execute(request, DepositCheckingResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<NotifyTransactionResponse>> notifyTransaction(NotifyTransactionRequest request) {
        return this.execute(request, NotifyTransactionResponse.class);
    }
}
