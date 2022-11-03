package vn.unicloud.umeepay.handler.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.constant.BaseConstant;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.auth.request.AdminLoginRequest;
import vn.unicloud.umeepay.dtos.auth.request.RootAdminLoginRequest;
import vn.unicloud.umeepay.dtos.common.LoginFailedData;
import vn.unicloud.umeepay.dtos.response.AccessTokenResponseCustom;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.UserStatus;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.AdminService;
import vn.unicloud.umeepay.service.KeycloakService;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

@Component
@Slf4j
@RequiredArgsConstructor
public class RootAdminLoginHandler extends RequestHandler<RootAdminLoginRequest, AccessTokenResponseCustom> {

    private final KeycloakService keycloakService;

    @Override
    public AccessTokenResponseCustom handle(RootAdminLoginRequest request) {
        try {
            AccessTokenResponseCustom response = keycloakService.getUserJWT(request.getUsername(), request.getPassword());
            if (response != null) {
                return response;
            }
        } catch (Exception e) {
            log.error("login error: {}", e.getMessage());
        }

        throw new InternalException(ResponseCode.INVALID_USERNAME_OR_PASSWORD);
    }
}
