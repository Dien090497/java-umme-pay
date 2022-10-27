package vn.unicloud.umeepay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IAuthController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.auth.AdminLoginRequest;
import vn.unicloud.umeepay.dtos.request.*;
import vn.unicloud.umeepay.dtos.response.*;

import javax.validation.Valid;

@RestController
public class AuthController extends BaseController implements IAuthController {

    @Override
    public ResponseEntity<ResponseBase<AccessTokenResponseCustom>> userLogin(LoginRequest request) {
        return this.execute(request, AccessTokenResponseCustom.class);
    }

    @Override
    public ResponseEntity<ResponseBase<AccessTokenResponseCustom>> clientLogin(@Valid @RequestBody ClientLoginRequest request) {
        return this.execute(request, AccessTokenResponseCustom.class);
    }

    @Override
    public ResponseEntity<ResponseBase<AccessTokenResponseCustom>> adminLogin(AdminLoginRequest request) {
        return this.execute(request, AccessTokenResponseCustom.class);
    }
}
