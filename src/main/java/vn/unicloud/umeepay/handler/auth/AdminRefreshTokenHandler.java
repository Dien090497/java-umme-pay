package vn.unicloud.umeepay.handler.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.TokenVerifier;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.auth.request.AdminRefreshTokenRequest;
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
public class AdminRefreshTokenHandler extends RequestHandler<AdminRefreshTokenRequest, AccessTokenResponseCustom> {

    private final KeycloakService keycloakService;

    private final RedisService redisService;

    @Override
    public AccessTokenResponseCustom handle(AdminRefreshTokenRequest request) {
        try {
            AccessToken token = TokenVerifier.create(request.getRefreshToken(), AccessToken.class).getToken();
            String id = token.getSubject();
            UserStatus status = redisService.getValue(RedisKeyUtils.getUserStatusKey(id), UserStatus.class);
            if (UserStatus.ACTIVE.equals(status)) {
                return keycloakService.refreshToken(request.getRefreshToken());
            }
        } catch (Exception ex) {
            log.error("Refresh access token failed {}", ex.getMessage());
        }
        throw new InternalException(ResponseCode.INVALID_REFRESH_TOKEN);
    }
}
