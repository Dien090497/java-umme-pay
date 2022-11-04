package vn.unicloud.umeepay.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IUserController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.user.request.*;
import vn.unicloud.umeepay.dtos.user.response.CheckOTPResponse;
import vn.unicloud.umeepay.dtos.user.response.CheckPhoneResponse;
import vn.unicloud.umeepay.dtos.user.response.UserResponse;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class UserController extends BaseController implements IUserController {

    @Override
    public ResponseEntity<ResponseBase<CheckPhoneResponse>> checkPhoneRegister(String phone) {
        return this.execute(new CheckPhoneRegisterRequest(phone), CheckPhoneResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<UserResponse>> register(CreateUserRequest request) {
        return this.execute(request, UserResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<CheckPhoneResponse>> checkPhone(String phone) {
        return this.execute(new CheckPhoneRequest(phone), CheckPhoneResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<CheckOTPResponse>> checkOTP(CheckOTPRequest request) {
        return this.execute(request, CheckOTPResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> changePassword(ChangePasswordRequest request) {
        return this.execute(request, StatusResponse.class);
    }

    //    @Override
//    public ResponseEntity<ResponseBase<VerifyEmailResponse>> verifyEmail(HttpServletRequest context, String token, String email) {
//        VerifyEmailRequest request = new VerifyEmailRequest(token, email);
//        request.setContext(context);
//        return this.execute(request, VerifyEmailResponse.class);
//    }
//
//
//    @Override
//    public ResponseEntity<ResponseBase<ChangePasswordResponse>> changePassword(Principal principal, HttpServletRequest context, ChangePasswordRequest request) {
//        request.setContext(context);
//        return this.execute(request, ChangePasswordResponse.class);
//    }
//
//    @Override
//    public ResponseEntity<ResponseBase<GetInfoResponse>> getInfo(Principal principal, HttpServletRequest context) {
//        GetInfoRequest request = new GetInfoRequest();
//        request.setContext(context);
//        return this.execute(request, GetInfoResponse.class);
//    }
//
//    @Override
//    public ResponseEntity<ResponseBase<ChangeInfoResponse>> changeInfo(HttpServletRequest context, ChangeInfoRequest request) {
//        request.setContext(context);
//        return this.execute(request, ChangeInfoResponse.class);
//    }

}
