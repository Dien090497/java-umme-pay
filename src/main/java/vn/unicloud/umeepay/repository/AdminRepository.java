package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.unicloud.umeepay.entity.Administrator;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Administrator, String>, JpaSpecificationExecutor<Administrator> {

    Optional<Administrator> findByUsername(String username);

    Optional<Administrator> findFirstByEmail(String email);

}
