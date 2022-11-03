package vn.unicloud.umeepay.handler.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.auth.request.LogoutRequest;
import vn.unicloud.umeepay.dtos.auth.request.RootAdminLoginRequest;
import vn.unicloud.umeepay.dtos.auth.response.LogoutResponse;
import vn.unicloud.umeepay.dtos.response.AccessTokenResponseCustom;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.KeycloakService;

@Component
@Slf4j
@RequiredArgsConstructor
public class LogoutHandler extends RequestHandler<LogoutRequest, LogoutResponse> {

    private final KeycloakService keycloakService;

    @Override
    public LogoutResponse handle(LogoutRequest request) {
        return keycloakService.logout(request);
    }
}
