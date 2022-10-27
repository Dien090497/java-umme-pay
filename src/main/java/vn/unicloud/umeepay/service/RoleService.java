package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.entity.merchant.UserRole;
import vn.unicloud.umeepay.repository.AdminRoleRepository;
import vn.unicloud.umeepay.repository.UserRoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final UserRoleRepository userRoleRepository;

    private final AdminRoleRepository adminRoleRepository;

    public UserRole getUserRole(String name) {
        if(name == null) {
            return null;
        }
        return userRoleRepository.findByName(name).orElse(null);
    }
}
