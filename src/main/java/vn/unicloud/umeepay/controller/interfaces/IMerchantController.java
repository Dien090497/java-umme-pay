package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.merchant.request.CreateMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.request.GetMerchantCredentialRequest;
import vn.unicloud.umeepay.dtos.merchant.request.UpdateMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.request.UpdateWebhookRequest;
import vn.unicloud.umeepay.dtos.merchant.response.*;
import vn.unicloud.umeepay.dtos.request.ClientLoginRequest;
import vn.unicloud.umeepay.dtos.request.LoginRequest;
import vn.unicloud.umeepay.dtos.response.AccessTokenResponseCustom;

import javax.validation.Valid;
import java.security.Principal;

@Tag(name = "Merchant Controller", description = "Thao tác với merchant")
@RequestMapping(value = "/api/merchant")
public interface IMerchantController {

    @Operation(
        summary = "Get merchant",
        description = "- Get merchant, yêu cầu từ user"
    )
    @GetMapping("/v1/getMerchant")
    ResponseEntity<ResponseBase<GetMerchantResponse>> getMerchant(Principal principal);

    @Operation(
        summary = "Tạo mới merchant",
        description = "- Tạo mới merchant, yêu cầu từ user"
    )
    @PostMapping("/v1/create")
    ResponseEntity<ResponseBase<CreateMerchantResponse>> create(Principal principal, @Valid @RequestBody CreateMerchantRequest request);

    @Operation(
        summary = "Cập nhật thông tin merchant",
        description = "- Cập nhật thông tin merchant"
    )
    @PutMapping("/v1/update")
    ResponseEntity<ResponseBase<UpdateMerchantResponse>> update(@Valid @RequestBody UpdateMerchantRequest request);

    @Operation(
        summary = "Cập nhật thông tin merchant",
        description = "- Cập nhật thông tin merchant"
    )
    @PutMapping("/v1/getCredential")
    ResponseEntity<ResponseBase<GetMerchantCredentialResponse>> getCredential(@Valid @RequestBody GetMerchantCredentialRequest request);

    @Operation(
        summary = "Cập nhật thông tin merchant",
        description = "- Cập nhật thông tin merchant"
    )
    @PutMapping("/v1/updateWebhook")
    ResponseEntity<ResponseBase<UpdateWebhookResponse>> updateWebhook(Principal principal, @Valid @RequestBody UpdateWebhookRequest request);
}
