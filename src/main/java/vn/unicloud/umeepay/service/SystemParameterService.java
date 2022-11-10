package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.entity.SystemParameter;
import vn.unicloud.umeepay.repository.SystemParameterRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SystemParameterService {

    private final SystemParameterRepository paramRepository;

    public List<SystemParameter> getAllParam(Specification<SystemParameter> spec, Sort sort) {
        if (spec == null || sort == null) {
            return new ArrayList<>();
        }
        return paramRepository.findAll(spec, sort);
    }

    public SystemParameter saveParam(SystemParameter parameter) {
        try {
            return paramRepository.save(parameter);
        } catch (Exception ex) {
            log.error("Save system parameter failed. {}", ex.getMessage());
        }
        return null;
    }

    public SystemParameter deleteParam(SystemParameter parameter) {
        try {
            paramRepository.delete(parameter);
            return parameter;
        } catch (Exception ex) {
            log.error("Delete system parameter failed. {}", ex.getMessage());
        }
        return null;
    }

    public SystemParameter getById(Long id) {
        try {
            return paramRepository
                    .findById(id)
                    .orElse(null);
        } catch (Exception ex) {
            log.error("Get system parameter failed. {}", ex.getMessage());
        }
        return null;
    }

    public SystemParameter getByName(String name) {
        try {
            return paramRepository
                    .findFirstByName(name)
                    .orElse(null);
        } catch (Exception ex) {
            log.error("Get system parameter failed. {}", ex.getMessage());
        }
        return null;
    }

}
