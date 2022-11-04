package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unicloud.umeepay.entity.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, String> {

    Merchant findFirstByUserId(String userId);

    Merchant findByCredentialId(String credentialId);

}
