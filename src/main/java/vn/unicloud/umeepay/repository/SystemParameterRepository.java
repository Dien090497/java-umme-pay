package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.entity.SystemParameter;

import java.util.Optional;

@Component
public interface SystemParameterRepository extends JpaRepository<SystemParameter, Long>, JpaSpecificationExecutor<SystemParameter> {

    Optional<SystemParameter> findFirstByName(String name);

}
