package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.unicloud.umeepay.config.OpenApiConfig;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.auth.request.AdminChangePasswordRequest;
import vn.unicloud.umeepay.dtos.auth.request.AdminRefreshTokenRequest;
import vn.unicloud.umeepay.dtos.auth.request.AdminLoginRequest;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.request.ClientLoginRequest;
import vn.unicloud.umeepay.dtos.request.LoginRequest;
import vn.unicloud.umeepay.dtos.response.AccessTokenResponseCustom;

import javax.validation.Valid;

@Tag(name = "Auth Controller", description = "Thao tác với auth")
@RequestMapping(value = "/api/auth")
public interface IAuthController {

    @Operation(
            summary = "login",
            description = "- login với username là email hoặc số điện thoại"
    )
    @RequestMapping(value = "/v1/user", method = RequestMethod.POST)
    ResponseEntity<ResponseBase<AccessTokenResponseCustom>> userLogin(@Valid @RequestBody LoginRequest request);

    @Operation(
            summary = "Client login",
            description = "- Client login oauth"
    )
    @RequestMapping(value = "/v1/client", method = RequestMethod.POST)
    ResponseEntity<ResponseBase<AccessTokenResponseCustom>> clientLogin(@Valid @RequestBody ClientLoginRequest request);

    //Admin login
    @Operation(
            summary = "Administrator login",
            description = "- Quản trị viên login với số username và password được cấp"
    )
    @RequestMapping(value = "/cms/v1/login", method = RequestMethod.POST)
    ResponseEntity<ResponseBase<AccessTokenResponseCustom>> adminLogin(@Valid @RequestBody AdminLoginRequest request);

    //Admin login
    @Operation(
            summary = "Refresh access token",
            description = "- Refresh access token"
    )
    @RequestMapping(value = "/cms/v1/refreshToken", method = RequestMethod.POST)
    ResponseEntity<ResponseBase<AccessTokenResponseCustom>> adminRefreshToken(@Valid @RequestBody AdminRefreshTokenRequest request);

    @Operation(
            summary = "Change admin password",
            description = "- Change admin password"
    )
    @RequestMapping(value = "/cms/v1/changePassword", method = RequestMethod.PUT)
    @SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
    ResponseEntity<ResponseBase<StatusResponse>> adminChangePassword(@Valid @RequestBody AdminChangePasswordRequest request);

}
