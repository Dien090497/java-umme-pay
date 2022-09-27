package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unicloud.umeepay.entity.Credential;

import java.util.UUID;

public interface CredentialRepository extends JpaRepository<Credential, String> {

}
