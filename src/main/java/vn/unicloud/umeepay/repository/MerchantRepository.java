package vn.unicloud.umeepay.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.Transaction;
import vn.unicloud.umeepay.enums.TransactionStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, String> {

    Merchant findByUserId(String userId);

    Merchant findByCredentialId(String credentialId);

}
