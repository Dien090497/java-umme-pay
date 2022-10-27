package vn.unicloud.umeepay.controller.interfaces;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.user.request.*;
import vn.unicloud.umeepay.dtos.user.response.*;

import javax.validation.Valid;

@RequestMapping("/api/portal/user")
@Tag(name = "User controller", description = "thao tác với user")
public interface IUserController {

    @Operation(summary = "Xác thực số điện thoại")
    @GetMapping("/v1/register/checkPhone")
    ResponseEntity<ResponseBase<CheckPhoneResponse>> checkPhoneRegister(@RequestParam String phone);

    @Operation(summary = "Tạo tài khoản người dùng")
    @PostMapping("/v1/register/submit")
    ResponseEntity<ResponseBase<UserResponse>> register(@RequestBody @Valid CreateUserRequest request);

    @Operation(summary = "Check Phone để thay đổi password")
    @PostMapping("/v1/changePassword/checkPhone")
    ResponseEntity<ResponseBase<CheckPhoneResponse>> checkPhone(@RequestParam String phone);

    @Operation(summary = "Kiểm tra OTP")
    @PostMapping("/v1/changePassword/checkOTP")
    ResponseEntity<ResponseBase<CheckOTPResponse>> checkOTP(@RequestBody @Valid CheckOTPRequest request);

    @Operation(summary = "Đổi mật khẩu")
    @PostMapping("/v1/changePassword/submit")
    ResponseEntity<ResponseBase<StatusResponse>> changePassword(@RequestBody @Valid ChangePasswordRequest request);

//    @Operation(
//            summary = "Xác thực mail đăng ký",
//            description = "Xác thực đăng ký bằng đường dẫn được gửi vào mail"
//    )
//    @GetMapping("/v1/verifyEmail")
//    ResponseEntity<ResponseBase<VerifyEmailResponse>> verifyEmail(HttpServletRequest context, @RequestParam("token") String token, @RequestParam("email") String email);

//    @Operation(
//            summary = "Đổi mật khẩu",
//            description = "Thay đổi mật khẩu user",
//            security = {@SecurityRequirement(name = "bearerAuth")}
//    )
//    @PutMapping(value = "/v1/changePassword")
//    ResponseEntity<ResponseBase<ChangePasswordResponse>> changePassword(Principal principal, HttpServletRequest context, @Valid @RequestBody ChangePasswordRequest request);

//    @Operation(
//            summary = "Lấy thông tin người dùng",
//            description = "Lấy các thông tin cơ bản của người dùng",
//            security = {@SecurityRequirement(name = "bearerAuth")}
//    )
//    @GetMapping(value = "/v1/getInfo")
//    ResponseEntity<ResponseBase<GetInfoResponse>> getInfo(Principal principal, HttpServletRequest context);

//    @Operation(
//            summary = "Đổi thông tin",
//            description = "Thay đổi thông tin người dùng",
//            security = {@SecurityRequirement(name = "bearerAuth")}
//    )
//    @PutMapping(value = "/v1/changeInfo")
//    ResponseEntity<ResponseBase<ChangeInfoResponse>> changeInfo(HttpServletRequest context, @RequestBody @Valid ChangeInfoRequest request);
}
