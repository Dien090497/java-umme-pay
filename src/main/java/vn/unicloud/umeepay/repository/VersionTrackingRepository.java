package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.unicloud.umeepay.entity.VersionTracking;
import vn.unicloud.umeepay.enums.SystemModule;

import java.util.Optional;

@Repository
public interface VersionTrackingRepository extends JpaRepository<VersionTracking, Long> {

    Optional<VersionTracking> findFirstByKey(SystemModule key);

}
