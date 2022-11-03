package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.umeepay.config.OpenApiConfig;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.auth.request.*;
import vn.unicloud.umeepay.dtos.auth.response.LogoutResponse;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.request.ClientLoginRequest;
import vn.unicloud.umeepay.dtos.request.LoginRequest;
import vn.unicloud.umeepay.dtos.response.AccessTokenResponseCustom;

import javax.validation.Valid;

@Tag(name = "Auth Controller", description = "Thao tác với auth")
@RequestMapping(value = "/api/auth")
public interface IAuthController {

    @Operation(
            summary = "user login",
            description = "- login với username là email hoặc số điện thoại"
    )
    @PostMapping("/portal/v1/login")
    ResponseEntity<ResponseBase<AccessTokenResponseCustom>> userLogin(@Valid @RequestBody LoginRequest request);

    @Operation(
        summary = "User refresh access token",
        description = "- refresh access token"
    )
    @PostMapping("/portal/v1/refreshToken")
    ResponseEntity<ResponseBase<AccessTokenResponseCustom>> refreshToken(@Valid @RequestBody RefreshTokenRequest request);

    @Operation(summary = "logout")
    @PostMapping("/v1/logout")
    ResponseEntity<ResponseBase<LogoutResponse>> logout(@Valid @RequestBody LogoutRequest request);

    @Operation(
            summary = "Client login",
            description = "- Client login oauth"
    )
    @PostMapping("/client/v1/login")
    ResponseEntity<ResponseBase<AccessTokenResponseCustom>> clientLogin(@Valid @RequestBody ClientLoginRequest request);

    //Admin login
    @Operation(
            summary = "Administrator login",
            description = "- Quản trị viên login với số username và password được cấp"
    )
    @PostMapping("/cms/v1/login")
    ResponseEntity<ResponseBase<AccessTokenResponseCustom>> adminLogin(@Valid @RequestBody AdminLoginRequest request);

    @Operation(
        summary = "Super admin login",
        description = "- Super admin login"
    )
    @PostMapping("/root/v1/login")
    ResponseEntity<ResponseBase<AccessTokenResponseCustom>> rootLogin(@Valid @RequestBody RootAdminLoginRequest request);

    //Admin login
    @Operation(
            summary = "Refresh access token",
            description = "- Refresh access token"
    )
    @PostMapping("/cms/v1/refreshToken")
    ResponseEntity<ResponseBase<AccessTokenResponseCustom>> refreshToken(@Valid @RequestBody AdminRefreshTokenRequest request);

    @Operation(
            summary = "Change admin password",
            description = "- Change admin password"
    )
    @PutMapping("/cms/v1/changePassword")
    @SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
    ResponseEntity<ResponseBase<StatusResponse>> adminChangePassword(@Valid @RequestBody AdminChangePasswordRequest request);

}
