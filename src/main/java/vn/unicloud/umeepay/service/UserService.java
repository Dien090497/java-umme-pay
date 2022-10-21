package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.dtos.user.request.CreateUserRequest;
import vn.unicloud.umeepay.dtos.user.response.CreateUserResponse;
import vn.unicloud.umeepay.entity.User;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final KeycloakService keycloakService;

    private final UserRepository userRepository;

    @SneakyThrows
    public CreateUserResponse createUser(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail().trim());
        user.setFullName(request.getFullName().trim());
        user.setPhone(request.getPhone().trim());
        String userId = keycloakService.createUser(user.getPhone(), request.getPassword(), user.getEmail(), user.getFullName());
        if (userId == null) {
            throw new InternalException(ResponseCode.CREATE_USER_FAILED);
        }
        user.setId(userId);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            keycloakService.deleteUser(userId);
            throw new InternalException(ResponseCode.CREATE_USER_FAILED);
        }

        return new CreateUserResponse(true);
    }

}
