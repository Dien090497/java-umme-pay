package vn.unicloud.umeepay.handler.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.request.LoginRequest;
import vn.unicloud.umeepay.dtos.response.AccessTokenResponseCustom;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.KeycloakService;

@Component
@Log4j2
@RequiredArgsConstructor
public class UserLoginHandler extends RequestHandler<LoginRequest, AccessTokenResponseCustom> {

    private final KeycloakService keycloakService;

    @Override
    public AccessTokenResponseCustom handle(LoginRequest request) {
        try {
            return keycloakService.getUserJWT(request.getUsername(), request.getPassword());
        } catch (Exception e) {
            log.error("login error: {}", e.getMessage());
        }
        throw new InternalException(ResponseCode.INVALID_USERNAME_OR_PASSWORD);
    }
}
