package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.entity.PermissionGroup;
import vn.unicloud.umeepay.entity.RoleGroup;
import vn.unicloud.umeepay.enums.RoleType;
import vn.unicloud.umeepay.repository.PermissionGroupRepository;
import vn.unicloud.umeepay.repository.RoleGroupRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    private final RoleGroupRepository roleRepository;

    private final PermissionGroupRepository perGroupRepository;

    /**
     * ROLES
     */

    public RoleGroup getRoleById(Long id) {
        if (id == null) {
            return null;
        }
        return roleRepository.findById(id).orElse(null);
    }

    public RoleGroup getRoleByCode(String code, RoleType scope) {
        if (code == null || scope == null) {
            return null;
        }
        return roleRepository.findByCodeAndScope(code, scope).orElse(null);
    }

    public RoleGroup saveRole(RoleGroup role) {
        try {
            return roleRepository.save(role);
        } catch (Exception ex) {
            log.error("Save role error, {}", ex.getMessage());
        }
        return null;
    }

    public Page<RoleGroup> getListRole(Specification<RoleGroup> spec, Pageable pageable) {
        if (spec == null || pageable == null) {
            return new PageImpl<>(new ArrayList<>());
        }

        return roleRepository.findAll(spec, pageable);
    }

    public RoleGroup deleteRole(RoleGroup role) {
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

    public List<PermissionGroup> getAllPermissions(RoleType scope) {
        if (scope == null) {
            return null;
        }
        return perGroupRepository.findAllByScope(scope);
    }

    public PermissionGroup savePermission(PermissionGroup per) {
        try {
            return perGroupRepository.save(per);
        } catch (Exception ex) {
            log.error("Save permission group failed, {}", ex.getMessage());
        }
        return null;
    }


}
