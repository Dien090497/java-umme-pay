package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unicloud.umeepay.entity.merchant.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByPhone(String phone);
}
