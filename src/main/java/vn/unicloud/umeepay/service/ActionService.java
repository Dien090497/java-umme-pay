package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.entity.Action;
import vn.unicloud.umeepay.repository.ActionRepository;

@Service
@RequiredArgsConstructor
public class ActionService {

    private final ActionRepository actionRepository;

    public Action getById(Long id) {
        if (id == null) {
            return null;
        }
        return actionRepository.findById(id).orElse(null);
    }
}
