package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {

}
