package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.entity.Role;
import vn.unicloud.umeepay.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getUserRole(String name) {
        if(name == null) {
            return null;
        }
        return roleRepository.findByName(name).orElse(null);
    }
}
