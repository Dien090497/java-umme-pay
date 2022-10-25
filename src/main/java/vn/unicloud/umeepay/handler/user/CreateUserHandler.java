package vn.unicloud.umeepay.handler.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.user.request.CreateUserRequest;
import vn.unicloud.umeepay.dtos.user.response.UserResponse;
import vn.unicloud.umeepay.entity.merchant.User;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.RoleType;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.KeycloakService;
import vn.unicloud.umeepay.service.UserService;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateUserHandler extends RequestHandler<CreateUserRequest, UserResponse> {

    private final KeycloakService keycloakService;

    private final UserService userService;

    @Override
    @Transactional
    public UserResponse handle(CreateUserRequest request) {
        String requestPhone = request.getPhone().trim();
        String requestPassword = request.getPassword();

        // validate
        if (userService.getUserByPhone(requestPhone) != null) {
            throw new InternalException(ResponseCode.EXISTED_PHONE);
        }

        User user = new User();
        user.setPhone(requestPhone);
        user.setUsername(requestPhone);

        List<String> merchantRoles = Arrays.asList(RoleType.MERCHANT.toString());
        String userId = keycloakService.createUser(user.getPhone(), requestPassword, user.getEmail(), user.getFullName(), merchantRoles);
        if (userId == null) {
            throw new InternalException(ResponseCode.CREATE_USER_FAILED);
        }

        user.setId(userId);

        User createdUser = userService.saveUser(user);
        if (createdUser == null) {
            keycloakService.deleteUser(userId);
            throw new InternalException(ResponseCode.CREATE_USER_FAILED);
        }

        return new UserResponse(createdUser);
    }
}
