package vn.unicloud.umeepay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IPaygateController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.paygate.request.DepositCheckingRequest;
import vn.unicloud.umeepay.dtos.paygate.request.InquiryCheckingRequest;
import vn.unicloud.umeepay.dtos.paygate.request.NotifyTransactionRequest;
import vn.unicloud.umeepay.dtos.paygate.response.DepositCheckingResponse;
import vn.unicloud.umeepay.dtos.paygate.response.InquiryCheckingResponse;
import vn.unicloud.umeepay.dtos.paygate.response.NotifyTransactionResponse;

import javax.validation.Valid;

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
    public ResponseEntity<ResponseBase<NotifyTransactionResponse>> notifyTransaction(@Valid @RequestBody NotifyTransactionRequest request) {
        return this.execute(request, NotifyTransactionResponse.class);
    }
}
