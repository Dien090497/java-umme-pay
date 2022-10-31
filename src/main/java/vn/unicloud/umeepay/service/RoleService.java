package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.dtos.role.request.GetListRoleRequest;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.entity.Permission;
import vn.unicloud.umeepay.entity.Role;
import vn.unicloud.umeepay.enums.RoleType;
import vn.unicloud.umeepay.repository.PermissionRepository;
import vn.unicloud.umeepay.repository.RoleRepository;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    /**
     * ROLES
     */

    public Role getRoleById(Long id) {
        if (id == null) {
            return null;
        }
        return roleRepository.findById(id).orElse(null);
    }

    public Role getRoleByCode(String code, RoleType scope) {
        if (code == null || scope == null) {
            return null;
        }
        return roleRepository.findByCodeAndScope(code, scope).orElse(null);
    }

    public Role saveRole(Role role) {
        try {
            return roleRepository.save(role);
        } catch (Exception ex) {
            log.error("Save role error, {}", ex.getMessage());
        }
        return null;
    }

    public Page<Role> getListRole(Specification<Role> spec, Pageable pageable) {
        if (spec == null || pageable == null) {
            return new PageImpl<>(new ArrayList<>());
        }

        return roleRepository.findAll(spec, pageable);
    }

    public List<Administrator> getAdminsInRole(Role role) {
        if (role == null || !role.getScope().equals(RoleType.ADMIN)) {
            return new ArrayList<>();
        }
        return roleRepository.findAdminsInRole(role.getId());
    }

    public Role deleteRole(Role role) {
        try {
            roleRepository.delete(role);
            return role;
        } catch (Exception ex) {
            log.error("Delete role error, {}", ex.getMessage());
        }
        return null;
    }

    /**
     * PERMISSIONS
     */


    public List<Permission> getAllPermissions(RoleType scope) {
        if (scope == null) {
            return null;
        }
        return permissionRepository.findAllByScope(scope);
    }

    public Permission savePermission(Permission per) {
        try {
            return permissionRepository.save(per);
        } catch (Exception ex) {
            log.error("Save permission failed, {}", ex.getMessage());
        }
        return null;
    }


}
