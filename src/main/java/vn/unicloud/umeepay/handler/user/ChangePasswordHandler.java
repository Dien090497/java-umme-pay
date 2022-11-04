package vn.unicloud.umeepay.handler.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.constant.BaseConstant;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.user.request.ChangePasswordRequest;
import vn.unicloud.umeepay.entity.User;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.model.ChangePasswordCache;
import vn.unicloud.umeepay.service.KeycloakService;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.service.UserService;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChangePasswordHandler extends RequestHandler<ChangePasswordRequest, StatusResponse> {

    private final KeycloakService keycloakService;

    private final UserService userService;

    private final RedisService redisService;

    @Override
    @Transactional
    public StatusResponse handle(ChangePasswordRequest request) {
        String requestPhone = request.getPhone().trim();
        String requestPassword = request.getPassword();

        // validate
        User user = userService.getUserByPhone(requestPhone);
        if (user == null) {
            throw new InternalException(ResponseCode.PHONE_NUMBER_INVALID);
        }

        String sessionKey = RedisKeyUtils.getChangePasswordSession(request.getSessionId());

        ChangePasswordCache passwordCache = redisService.getValue(
            sessionKey,
            ChangePasswordCache.class
        );

        if (passwordCache == null ||
            !Objects.equals(passwordCache.getSessionId(), request.getSessionId()) ||
            !Objects.equals(passwordCache.getPhone(), request.getPhone()) ||
            !Objects.equals(passwordCache.getToken(), request.getToken())) {
            log.error("Invalid Session");
            throw new InternalException(ResponseCode.PASSWORD_CHANGE_FAILED);
        }

        redisService.deleteKey(sessionKey);

        if (!keycloakService.setUserPassword(user.getSubjectId(), requestPassword)) {
            log.error("Change password to keycloak failed");
            throw new InternalException(ResponseCode.PASSWORD_CHANGE_FAILED);
        }

        return new StatusResponse(true);
    }
}
