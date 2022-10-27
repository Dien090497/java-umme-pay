package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.entity.merchant.User;
import vn.unicloud.umeepay.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     *
     * @param user
     * @return User
     */
    public User saveUser(User user) {
        if (user == null) {
            return null;
        }

        try {
            return userRepository.save(user);
        } catch (Exception ex) {
            log.error("Save user error. {}", ex.getMessage());
        }

        return null;
    }


    /**
     *
     * @param phone
     * @return user
     */
    public User getUserByPhone(String phone) {
        if (phone == null) {
            return null;
        }
        return userRepository.findByPhone(phone).orElse(null);
    }
}
