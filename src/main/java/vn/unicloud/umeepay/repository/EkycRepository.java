package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.unicloud.umeepay.entity.Ekyc;

@Repository
public interface EkycRepository extends JpaRepository<Ekyc, Long> {
}
