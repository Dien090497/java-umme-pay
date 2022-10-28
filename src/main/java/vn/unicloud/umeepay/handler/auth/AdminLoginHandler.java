package vn.unicloud.umeepay.handler.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.auth.AdminLoginRequest;
import vn.unicloud.umeepay.dtos.common.LoginFailedData;
import vn.unicloud.umeepay.dtos.request.LoginRequest;
import vn.unicloud.umeepay.dtos.response.AccessTokenResponseCustom;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.UserStatus;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.AdminService;
import vn.unicloud.umeepay.service.KeycloakService;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.utils.Constants;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

import java.sql.Timestamp;
import java.util.Arrays;

@Component
@Slf4j
@RequiredArgsConstructor
public class AdminLoginHandler extends RequestHandler<AdminLoginRequest, AccessTokenResponseCustom> {

    private final KeycloakService keycloakService;

    private final AdminService adminService;

    private final RedisService redisService;

    @Override
    public AccessTokenResponseCustom handle(AdminLoginRequest request) {
        Administrator admin = adminService.getByUsername(request.getUsername());
        if (admin == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }

        if (!UserStatus.ACTIVE.equals(admin.getStatus())) {
            throw new InternalException(ResponseCode.INACTIVE_ACCOUNT);
        }

        try {
            AccessTokenResponseCustom response = keycloakService.getUserJWT(request.getUsername(), request.getPassword());
            if (response != null) {
                // Mark as user logged into the system
                if (!admin.getLoggedIn()) {
                    admin.setLoggedIn(true);
                    adminService.saveAdmin(admin);
                }

                // Update redis data
                redisService.setValue(RedisKeyUtils.getUserStatusKey(admin.getId()), UserStatus.ACTIVE);
                redisService.deleteKey(RedisKeyUtils.getLoginFailedData(admin.getId()));

                return response;
            }
        } catch (Exception e) {
            log.error("login error: {}", e.getMessage());
        }

        boolean isBlocked = handleLoginFailed(admin);
        if (isBlocked) {
            throw new InternalException(ResponseCode.BLOCKED_ACCOUNT);
        }

        throw new InternalException(ResponseCode.INVALID_USERNAME_OR_PASSWORD);
    }

    private boolean handleLoginFailed(Administrator admin) {
        String redisKey = RedisKeyUtils.getLoginFailedData(admin.getId());
        LoginFailedData loginFailedData = redisService.getValue(redisKey, LoginFailedData.class);

        loginFailedData = loginFailedData != null
                ? loginFailedData
                : new LoginFailedData();

        Integer totalTimes = loginFailedData.getTotalTimes() + 1;
        loginFailedData.setTotalTimes(totalTimes);
        redisService.setValue(redisKey, loginFailedData);

        if (totalTimes < Constants.MAX_LOGIN_FAILED_TIMES) {
            redisService.setExpire(redisKey, Constants.LOGIN_FAILED_TIMEOUT);
            return false;
        }

        // Block user when log in allowed overtimes
        redisService.setValue(RedisKeyUtils.getUserStatusKey(admin.getId()), UserStatus.BLOCKED);
        admin.setStatus(UserStatus.BLOCKED);
        adminService.saveAdmin(admin);
        return true;
    }
}
