package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unicloud.umeepay.entity.merchant.User;

public interface UserRepository extends JpaRepository<User, String> {

}
