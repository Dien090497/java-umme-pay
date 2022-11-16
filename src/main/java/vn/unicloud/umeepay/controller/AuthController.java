package vn.unicloud.umeepay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IAuthController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.auth.request.AdminChangePasswordRequest;
import vn.unicloud.umeepay.dtos.auth.request.AdminLogoutRequest;
import vn.unicloud.umeepay.dtos.auth.request.AdminRefreshTokenRequest;
import vn.unicloud.umeepay.dtos.auth.request.AdminLoginRequest;
import vn.unicloud.umeepay.dtos.auth.request.*;
import vn.unicloud.umeepay.dtos.auth.response.LogoutResponse;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.request.*;
import vn.unicloud.umeepay.dtos.response.*;

import javax.validation.Valid;

@RestController
public class AuthController extends BaseController implements IAuthController {

    @Override
    public ResponseEntity<ResponseBase<AccessTokenResponseCustom>> userLogin(LoginRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<AccessTokenResponseCustom>> refreshToken(RefreshTokenRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<LogoutResponse>> logout(LogoutRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<AccessTokenResponseCustom>> clientLogin(@Valid @RequestBody ClientLoginRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<AccessTokenResponseCustom>> adminLogin(AdminLoginRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<AccessTokenResponseCustom>> rootLogin(RootAdminLoginRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<AccessTokenResponseCustom>> refreshToken(AdminRefreshTokenRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> adminChangePassword(AdminChangePasswordRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> adminLogout(AdminLogoutRequest request) {
        return this.execute(request);
    }

}
