package vn.unicloud.vietqr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.vietqr.controller.interfaces.IAuthController;
import vn.unicloud.vietqr.core.BaseController;
import vn.unicloud.vietqr.core.ResponseBase;
import vn.unicloud.vietqr.dtos.request.*;
import vn.unicloud.vietqr.dtos.response.*;

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
}
