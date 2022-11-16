package vn.unicloud.umeepay.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IPayLinkController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.paylink.request.*;
import vn.unicloud.umeepay.dtos.paylink.response.*;
import vn.unicloud.umeepay.service.SecurityService;

@RestController
@RequiredArgsConstructor
public class PayLinkController extends BaseController implements IPayLinkController {

    private final SecurityService securityService;

    @Override
    public ResponseEntity<ResponseBase<PayLinkResponse>> create(CreatePayLinkRequest request) {
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetPayLinkResponse>> getDetail(String payLinkCode) {
        return this.execute(new GetPayLinkRequest(payLinkCode));
    }

    @Override
    public ResponseEntity<ResponseBase<GetPayLinkVietQRResponse>> payVietQR(String payLinkCode) {
        return this.execute(new GetPayLinkVietQRRequest(payLinkCode));
    }

    @Override
    public ResponseEntity<ResponseBase<GetPayLinkBankTransferResponse>> payBankTransfer(String payLinkCode) {
        return this.execute(new GetPayLinkBankTransferRequest(payLinkCode));
    }

    @Override
    public ResponseEntity<ResponseBase<PayLinkStatusResponse>> query(String payLinkCode) {
        return this.execute(new QueryPayLinkRequest(payLinkCode));
    }
}
