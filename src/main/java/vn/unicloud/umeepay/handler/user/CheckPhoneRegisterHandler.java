package vn.unicloud.umeepay.handler.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.constant.BaseConstant;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.user.request.CheckPhoneRegisterRequest;
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
public class CheckPhoneRegisterHandler extends RequestHandler<CheckPhoneRegisterRequest, CheckPhoneResponse> {

    private final UserService userService;

    @Override
    public CheckPhoneResponse handle(CheckPhoneRegisterRequest request) {
        String requestPhone = request.getPhone().trim();

        // validate
        if (userService.getUserByPhone(requestPhone) != null) {
            log.error("Existed phone");
            throw new InternalException(ResponseCode.EXISTED_PHONE);
        }

        return userService.checkPhone(requestPhone);
    }
}
