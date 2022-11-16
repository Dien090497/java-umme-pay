package vn.unicloud.umeepay.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.unicloud.umeepay.controller.interfaces.IMerchantController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.merchant.request.*;
import vn.unicloud.umeepay.dtos.merchant.response.*;
import vn.unicloud.umeepay.service.SecurityService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MerchantController extends BaseController implements IMerchantController {

    private final SecurityService securityService;

    @Override
    public ResponseEntity<ResponseBase<GetMerchantResponse>> getMerchant() {
        GetMerchantRequest request = new GetMerchantRequest();
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<CreateMerchantResponse>> create(CreateMerchantRequest request) {
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<UpdateMerchantResponse>> update(UpdateMerchantRequest request) {
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> detectCardFront(MultipartFile file) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> detectCardBack(MultipartFile file) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseBase<SubmitProfileInfoResponse>> submitInfo(SubmitMerchantInfoRequest request) {
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetListBankAccountResponse>> getListBankAccount(GetListBankAccountRequest request) {
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<CheckBankAccountResponse>> checkBankAccount(CheckBankAccountRequest request) {
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<SubmitLinkAccountResponse>> submitLinkAccount(SubmitLinkAccountRequest request) {
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<VerifyLinkAccountResponse>> checkOTPLinkAccount(VerifyLinkAccountRequest request) {
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetMerchantCredentialResponse>> getCredential(GetMerchantCredentialRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<UpdateWebhookResponse>> updateWebhook(UpdateWebhookRequest request) {
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }
}
