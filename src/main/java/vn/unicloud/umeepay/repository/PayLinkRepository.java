package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.unicloud.umeepay.entity.Ekyc;
import vn.unicloud.umeepay.entity.PayLink;

public interface PayLinkRepository extends JpaRepository<PayLink, String> {

    PayLink findFirstByCode(String code);

}
