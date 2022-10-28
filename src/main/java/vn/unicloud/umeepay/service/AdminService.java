package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.repository.AdminRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;

    public Administrator getById(String id) {
        if (id == null) {
            return null;
        }
        return adminRepository
                .findById(id)
                .orElse(null);
    }

    public Administrator getByUsername(String username) {
        if (username == null) {
            return null;
        }
        return adminRepository
                .findByUsername(username)
                .orElse(null);
    }

    public Administrator getByEmail(String email) {
        if (email == null) {
            return null;
        }
        return adminRepository
                .findByEmail(email)
                .orElse(null);
    }

    public Administrator saveAdmin(Administrator admin) {
        if (admin == null) {
            return null;
        }
        return adminRepository.save(admin);
    }

    public Administrator deleteAdmin(Administrator admin) {
        if (admin == null) {
            return null;
        }

        try {
            adminRepository.delete(admin);
            return admin;
        } catch (Exception ex) {
            log.error("Delete admin failed, {}", ex.getMessage());
        }

        return null;
    }

    public Page<Administrator> getAll(Specification<Administrator> spec, Pageable pageable) {
        if (spec == null || pageable == null) {
            return new PageImpl<>(new ArrayList<>());
        }
        return adminRepository.findAll(spec, pageable);
    }

}
