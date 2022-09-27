package vn.unicloud.umeepay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IMerchantController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.merchant.request.CreateMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.request.GetMerchantCredentialRequest;
import vn.unicloud.umeepay.dtos.merchant.request.GetMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.request.UpdateMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.response.CreateMerchantResponse;
import vn.unicloud.umeepay.dtos.merchant.response.GetMerchantCredentialResponse;
import vn.unicloud.umeepay.dtos.merchant.response.GetMerchantResponse;
import vn.unicloud.umeepay.dtos.merchant.response.UpdateMerchantResponse;

import java.security.Principal;

@RestController
public class MerchantController extends BaseController implements IMerchantController {

    @Override
    public ResponseEntity<ResponseBase<GetMerchantResponse>> getMerchant(Principal principal) {
        GetMerchantRequest request = new GetMerchantRequest();
        request.setUserId(principal.getName());
        return this.execute(request, GetMerchantResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<CreateMerchantResponse>> create(Principal principal, CreateMerchantRequest request) {
        request.setUserId(principal.getName());
        return this.execute(request, CreateMerchantResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<UpdateMerchantResponse>> update(UpdateMerchantRequest request) {
        return this.execute(request, UpdateMerchantResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<GetMerchantCredentialResponse>> getCredential(GetMerchantCredentialRequest request) {
        return this.execute(request, GetMerchantCredentialResponse.class);
    }
}
