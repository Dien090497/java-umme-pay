package vn.unicloud.umeepay.handler.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.TokenVerifier;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.auth.request.AdminRefreshTokenRequest;
import vn.unicloud.umeepay.dtos.auth.request.RefreshTokenRequest;
import vn.unicloud.umeepay.dtos.response.AccessTokenResponseCustom;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.UserStatus;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.KeycloakService;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

@Component
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenHandler extends RequestHandler<RefreshTokenRequest, AccessTokenResponseCustom> {

    private final KeycloakService keycloakService;

    @Override
    public AccessTokenResponseCustom handle(RefreshTokenRequest request) {
        try {
            return keycloakService.refreshToken(request.getRefreshToken());
        } catch (Exception ex) {
            log.error("Refresh access token failed {}", ex.getMessage());
        }
        throw new InternalException(ResponseCode.INVALID_REFRESH_TOKEN);
    }
}
