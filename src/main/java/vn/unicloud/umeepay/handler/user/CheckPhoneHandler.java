package vn.unicloud.umeepay.handler.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.constant.BaseConstant;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.user.request.CheckPhoneRequest;
import vn.unicloud.umeepay.dtos.user.response.CheckPhoneResponse;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.model.OTPKey;
import vn.unicloud.umeepay.service.KeycloakService;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.service.UserService;
import vn.unicloud.umeepay.utils.CommonUtils;

@Component
@Slf4j
@RequiredArgsConstructor
public class CheckPhoneHandler extends RequestHandler<CheckPhoneRequest, CheckPhoneResponse> {

    private final KeycloakService keycloakService;

    private final UserService userService;

    private final RedisService redisService;

    @Value("${umeepay.hardcode-otp:true}")
    private boolean hardCodeOTP;

    @Value("${umeepay.otp-expire-s:60}")
    private int otpExpire;

    @Override
    public CheckPhoneResponse handle(CheckPhoneRequest request) {
        String requestPhone = request.getPhone().trim();

        // validate
        if (userService.getUserByPhone(requestPhone) != null) {
            log.error("Existed phone");
            throw new InternalException(ResponseCode.EXISTED_PHONE);
        }

        if (redisService.exist(BaseConstant.OTP_KEY + requestPhone)) {
            log.error("Existed OTP");
            throw new InternalException(ResponseCode.EXISTED_OTP);
        }

        String sessionId = CommonUtils.generateUUID();
        String otp = CommonUtils.getOTP(hardCodeOTP);
        // TODO: Send otp
        OTPKey key = new OTPKey(otp, requestPhone, sessionId);
        log.debug("OTP info: {}", key);

        redisService.setValue(BaseConstant.OTP_KEY + requestPhone, key);
        redisService.setExpire(BaseConstant.OTP_KEY + requestPhone, otpExpire);

        return new CheckPhoneResponse(sessionId, otpExpire);
    }
}
