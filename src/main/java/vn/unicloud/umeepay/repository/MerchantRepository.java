package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.unicloud.umeepay.entity.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, String> {

    @Query("SELECT merchant " +
            "FROM User user " +
            "LEFT JOIN user.merchant merchant " +
            "WHERE user.id = ?1")
    Merchant findByUserId(String userId);

    Merchant findByCredentialId(String credentialId);

}
