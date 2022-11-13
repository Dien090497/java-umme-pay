package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unicloud.umeepay.entity.BankAccount;
import vn.unicloud.umeepay.entity.Credential;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
