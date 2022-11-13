package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.unicloud.umeepay.config.OpenApiConfig;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.merchant.request.*;
import vn.unicloud.umeepay.dtos.merchant.response.*;

import javax.validation.Valid;
import java.security.Principal;

@Tag(name = "Merchant Controller", description = "Thao tác với merchant")
@RequestMapping(value = "/api/merchant")
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public interface IMerchantController {

    @Operation(
        summary = "Cập nhật thông tin merchant",
        description = "- Cập nhật thông tin merchant"
    )
    @PutMapping("/portal/v1/updateMerchant")
    ResponseEntity<ResponseBase<UpdateMerchantResponse>> update(@Valid @RequestBody UpdateMerchantRequest request);

    @Operation(
        summary = "Get merchant",
        description = "- Get merchant, yêu cầu từ user"
    )
    @GetMapping("/portal/v1/getMerchant")
    ResponseEntity<ResponseBase<GetMerchantResponse>> getMerchant();

    @Operation(
        summary = "Tạo mới merchant",
        description = "- Tạo mới merchant, yêu cầu từ user"
    )
//    @PostMapping("/portal/v1/createMerchant")
    ResponseEntity<ResponseBase<CreateMerchantResponse>> create(@Valid @RequestBody CreateMerchantRequest request);

    @Operation(summary = "Nhận diện ảnh mặt trước")
    @PostMapping("/portal/v1/submitInfo/ocr/front")
    ResponseEntity<ResponseBase<StatusResponse>> detectCardFront(@RequestParam("file") MultipartFile file);

    @Operation(summary = "Nhận diện ảnh mặt sau")
    @PostMapping("/portal/v1/submitInfo/ocr/back")
    ResponseEntity<ResponseBase<StatusResponse>> detectCardBack(@RequestParam("file") MultipartFile file);

    @Operation(summary = "Cập nhật thông tin merchant")
    @PostMapping("/portal/v1/submitInfo")
    ResponseEntity<ResponseBase<SubmitProfileInfoResponse>> submitInfo(@Valid @RequestBody SubmitMerchantInfoRequest request);

    @Operation(summary = "Cập nhật thông tin merchant")
    @PostMapping("/portal/v1/getListBankAccount")
    ResponseEntity<ResponseBase<GetListBankAccountResponse>> getListBankAccount(@Valid @RequestBody GetListBankAccountRequest request);

    @Operation(summary = "Liên kết tài khoản - Kiểm tra")
    @PostMapping("/portal/v1/linkAccount/check")
    ResponseEntity<ResponseBase<CheckBankAccountResponse>> checkBankAccount(@Valid @RequestBody CheckBankAccountRequest request);

    @Operation(summary = "Liên kết tài khoản - Gửi yêu cầu")
    @PostMapping("/portal/v1/linkAccount/submit")
    ResponseEntity<ResponseBase<SubmitLinkAccountResponse>> submitLinkAccount(@Valid @RequestBody SubmitLinkAccountRequest request);

    @Operation(summary = "Liên kết tài khoản - Kiểm tra OTP")
    @PostMapping("/portal/v1/linkAccount/verifyOTP")
    ResponseEntity<ResponseBase<VerifyLinkAccountResponse>> checkOTPLinkAccount(@Valid @RequestBody VerifyLinkAccountRequest request);

    @Operation(
        summary = "Cập nhật thông tin merchant",
        description = "- Cập nhật thông tin merchant"
    )
    @PutMapping("/portal/v1/getCredential")
    ResponseEntity<ResponseBase<GetMerchantCredentialResponse>> getCredential(@Valid @RequestBody GetMerchantCredentialRequest request);

    @Operation(
        summary = "Cập nhật thông tin merchant",
        description = "- Cập nhật thông tin merchant"
    )
    @PutMapping("/portal/v1/updateWebhook")
    ResponseEntity<ResponseBase<UpdateWebhookResponse>> updateWebhook(@Valid @RequestBody UpdateWebhookRequest request);
}
