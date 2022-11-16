package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.unicloud.umeepay.entity.Customer;
import vn.unicloud.umeepay.entity.CustomerGroup;

import java.util.List;

public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, String> {

    CustomerGroup findFirstByIdAndMerchantId(String id, String merchantId);

    List<CustomerGroup> findAllByMerchantId(String merchantId);
}
