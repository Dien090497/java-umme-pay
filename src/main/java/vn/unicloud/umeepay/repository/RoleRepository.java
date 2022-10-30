package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.entity.Role;
import vn.unicloud.umeepay.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    Optional<Role> findByName(String name);

    Optional<Role> findByCode(String code);

    @Query("SELECT admin FROM Administrator admin " +
            "LEFT JOIN Role role " +
            "ON admin.role IS NOT NULL " +
            "   AND admin.role.id = role.id " +
            "WHERE  role.id = ?1")
    List<Administrator> findAdminsInRole(Long id);

}
