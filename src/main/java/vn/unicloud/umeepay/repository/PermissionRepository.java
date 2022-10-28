package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.unicloud.umeepay.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
