package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.constant.BaseConstant;
import vn.unicloud.umeepay.dtos.user.response.CheckPhoneResponse;
import vn.unicloud.umeepay.entity.User;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.model.OTPKey;
import vn.unicloud.umeepay.repository.UserRepository;
import vn.unicloud.umeepay.utils.CommonUtils;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RedisService redisService;

    @Value("${umeepay.hardcode-otp:true}")
    private boolean hardCodeOTP;

    @Value("${umeepay.otp-expire-s:60}")
    private int otpExpire;

    /**
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

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    /**
     * @param phone
     * @return user
     */
    public User getUserByPhone(String phone) {
        if (phone == null) {
            return null;
        }
        return userRepository.findByPhone(phone).orElse(null);
    }

    public User getUserById(String id) {
        if (id == null) {
            return null;
        }
        return userRepository.findById(id).orElse(null);
    }

    public User getUserBySubjectId(String id) {
        if (id == null) {
            return null;
        }
        return userRepository.findFirstBySubjectId(id).orElse(null);
    }

    @SneakyThrows
    public CheckPhoneResponse checkPhone(String phone, String signature) {
        String otpKey = RedisKeyUtils.getOtpKey(phone);
        if (redisService.exist(otpKey)) {
            log.error("Existed OTP");
            throw new InternalException(ResponseCode.EXISTED_OTP);
        }

        String sessionId = CommonUtils.generateUUID();
        String otp = CommonUtils.getOTP(hardCodeOTP);
        // TODO: Send otp
        OTPKey key = new OTPKey(otp, phone, sessionId, signature);
        log.debug("Phone: {}, OTP info: {}", key.getPhone(), key.getOtp());

        redisService.setValue(otpKey, key);
        redisService.setExpire(otpKey, otpExpire);

        return new CheckPhoneResponse(sessionId, otpExpire);
    }

    /**
     * Get merchant members
     * @param spec
     * @param pageable
     * @return
     */
    public Page<User> getAllUsers(Specification<User> spec, Pageable pageable) {
        if (spec == null || pageable == null) {
            return new PageImpl<>(new ArrayList<>());
        }

        return userRepository.findAll(spec, pageable);
    }
}
