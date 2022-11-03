package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.unicloud.umeepay.entity.PermissionGroup;
import vn.unicloud.umeepay.enums.RoleType;

import java.util.List;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {

    List<PermissionGroup> findAllByScope(RoleType scope);

}
