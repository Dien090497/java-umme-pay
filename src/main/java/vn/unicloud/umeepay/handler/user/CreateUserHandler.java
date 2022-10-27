package vn.unicloud.umeepay.handler.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.constant.BaseConstant;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.user.request.CreateUserRequest;
import vn.unicloud.umeepay.dtos.user.response.UserResponse;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.User;
import vn.unicloud.umeepay.enums.MerchantStatus;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.RoleType;
import vn.unicloud.umeepay.enums.UserStatus;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.model.OTPKey;
import vn.unicloud.umeepay.service.KeycloakService;
import vn.unicloud.umeepay.service.MerchantService;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.service.UserService;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateUserHandler extends RequestHandler<CreateUserRequest, UserResponse> {

    private final KeycloakService keycloakService;

    private final UserService userService;

    private final MerchantService merchantService;

    private final RedisService redisService;

    @Override
    @Transactional
    public UserResponse handle(CreateUserRequest request) {
        String requestPhone = request.getPhone().trim();
        String requestPassword = request.getPassword();

        // validate
        if (userService.getUserByPhone(requestPhone) != null) {
            throw new InternalException(ResponseCode.EXISTED_PHONE);
        }

        String phoneKey = BaseConstant.OTP_KEY + requestPhone;

        OTPKey otpKey = redisService.getValue(phoneKey, OTPKey.class);

        if (otpKey == null ||
            !Objects.equals(otpKey.getPhone(), request.getPhone()) ||
            !Objects.equals(otpKey.getOtp(), request.getOtp())) {
            log.error("Invalid OTP");
            throw new InternalException(ResponseCode.OTP_INVALID);
        }

        if (!Objects.equals(otpKey.getSessionId(), request.getSessionId())) {
            log.error("Invalid session");
            throw new InternalException(ResponseCode.INVALID_SESSION);
        }

        redisService.deleteKey(phoneKey);

        User user = new User();
        user.setPhone(requestPhone);
        user.setUsername(requestPhone);
        user.setStatus(UserStatus.CREATED);

        List<String> merchantRoles = List.of(RoleType.MERCHANT.toString());
        String userId = keycloakService.createUser(user.getPhone(), requestPassword, null, null, merchantRoles);
        if (userId == null) {
            throw new InternalException(ResponseCode.CREATE_USER_FAILED);
        }

        user.setSubjectId(userId);

        User createdUser = userService.saveUser(user);
        if (createdUser == null) {
            keycloakService.deleteUser(userId);
            throw new InternalException(ResponseCode.CREATE_USER_FAILED);
        }

        Merchant merchant = Merchant.builder()
            .status(MerchantStatus.CREATED)
            .build();

        merchantService.saveMerchant(merchant);

        return new UserResponse(createdUser);
    }
}
