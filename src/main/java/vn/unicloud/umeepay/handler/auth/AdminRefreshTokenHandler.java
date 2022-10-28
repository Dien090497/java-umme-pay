package vn.unicloud.umeepay.handler.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.admin.request.AdminRefreshTokenRequest;
import vn.unicloud.umeepay.dtos.response.AccessTokenResponseCustom;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.KeycloakService;

@Component
@Slf4j
@RequiredArgsConstructor
public class AdminRefreshTokenHandler extends RequestHandler<AdminRefreshTokenRequest, AccessTokenResponseCustom> {

    private final KeycloakService keycloakService;


    @Override
    public AccessTokenResponseCustom handle(AdminRefreshTokenRequest request) {
        try {
            return keycloakService.refreshToken(request.getRefreshToken());
        } catch (Exception ex) {
            log.error("Refresh access token failed {}", ex.getMessage());
        }
        throw new InternalException(ResponseCode.INVALID_REFRESH_TOKEN);
    }
}
