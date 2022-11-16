package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unicloud.umeepay.entity.Credential;
import vn.unicloud.umeepay.entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    Customer findFirstByIdAndMerchantId(String id, String merchantId);

    List<Customer> findAllByMerchantId(String merchantId);

}
