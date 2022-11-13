package vn.unicloud.umeepay.handler.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.constant.BaseConstant;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.user.request.CheckOTPRequest;
import vn.unicloud.umeepay.dtos.user.request.CheckPhoneRequest;
import vn.unicloud.umeepay.dtos.user.response.CheckOTPResponse;
import vn.unicloud.umeepay.dtos.user.response.CheckPhoneResponse;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.model.ChangePasswordCache;
import vn.unicloud.umeepay.model.OTPKey;
import vn.unicloud.umeepay.service.KeycloakService;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.service.UserService;
import vn.unicloud.umeepay.utils.CommonUtils;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class CheckOTPHandler extends RequestHandler<CheckOTPRequest, CheckOTPResponse> {

    private final UserService userService;

    private final RedisService redisService;

    @Value("${umeepay.change-password-timeout:900}")
    private int changePasswordTimeout;

    @Override
    public CheckOTPResponse handle(CheckOTPRequest request) {
        String requestPhone = request.getPhone().trim();

        // validate
        if (userService.getUserByPhone(requestPhone) == null) {
            log.error("Invalid phone number");
            throw new InternalException(ResponseCode.PHONE_NUMBER_INVALID);
        }

        String phoneKey = RedisKeyUtils.getOtpKey(requestPhone);

        OTPKey otpKey = redisService.getValue(phoneKey, OTPKey.class);

        if (otpKey == null ||
            !Objects.equals(otpKey.getSessionId(), request.getSessionId()) ||
            !Objects.equals(otpKey.getPhone(), request.getPhone()) ||
            !Objects.equals(otpKey.getSignature(), CommonUtils.md5(request.getPhone())) ||
            !Objects.equals(otpKey.getOtp(), request.getOtp())) {
            log.error("Invalid OTP");
            throw new InternalException(ResponseCode.OTP_INVALID);
        }

        redisService.deleteKey(phoneKey);

        String token = CommonUtils.getSecureRandomKey(128);

        ChangePasswordCache passwordCache = new ChangePasswordCache(
            otpKey.getSessionId(),
            otpKey.getPhone(),
            token
        );

        String sessionKey = RedisKeyUtils.getChangePasswordSession(otpKey.getSessionId());

        redisService.setValue(sessionKey, passwordCache);
        redisService.setExpire(sessionKey, changePasswordTimeout);

        return new CheckOTPResponse(otpKey.getSessionId(), token, changePasswordTimeout);
    }
}
