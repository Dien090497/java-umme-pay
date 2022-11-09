package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import vn.unicloud.umeepay.entity.Merchant;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, String>, JpaSpecificationExecutor<Merchant> {

    Merchant findFirstByUserId(String userId);

    Merchant findByCredentialId(String credentialId);

    @Query("SELECT merchant " +
            "FROM User user " +
            "JOIN user.merchant merchant " +
            "WHERE user.id = ?1")
    Optional<Merchant> findByUserId(String userId);
}
