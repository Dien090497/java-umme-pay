package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unicloud.umeepay.entity.merchant.Credential;

public interface CredentialRepository extends JpaRepository<Credential, String> {

}
