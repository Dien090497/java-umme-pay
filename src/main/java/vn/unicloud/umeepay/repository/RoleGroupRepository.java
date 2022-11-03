package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.entity.RoleGroup;
import vn.unicloud.umeepay.enums.RoleType;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleGroupRepository extends JpaRepository<RoleGroup, Long>, JpaSpecificationExecutor<RoleGroup> {

    Optional<RoleGroup> findByCodeAndScope(String code, RoleType scope);

}
