package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.entity.VersionTracking;
import vn.unicloud.umeepay.enums.SystemModule;
import vn.unicloud.umeepay.repository.VersionTrackingRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class VersionTrackingService {

    private final VersionTrackingRepository trackingRepository;

    public VersionTracking save(VersionTracking tracking) {
        try {
            return trackingRepository.save(tracking);
        } catch (Exception ex) {
            log.error("Save tracking failed, {}", ex.getMessage());
        }
        return null;
    }

    public VersionTracking getByKey(SystemModule key) {
        try {
            return trackingRepository.findFirstByKey(key).orElse(null);
        } catch (Exception ex) {
            log.error("Get tracking failed, {}", ex.getMessage());
        }
        return null;
    }

}
