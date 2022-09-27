package vn.unicloud.umeepay.handler.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.user.request.CreateUserRequest;
import vn.unicloud.umeepay.dtos.user.response.CreateUserResponse;
import vn.unicloud.umeepay.service.UserService;

@Component
@Slf4j
public class CreateUserHandler extends RequestHandler<CreateUserRequest, CreateUserResponse> {

    @Autowired
    private UserService userService;

    @Override
    public CreateUserResponse handle(CreateUserRequest request) {
        return userService.createUser(request);
    }
}
