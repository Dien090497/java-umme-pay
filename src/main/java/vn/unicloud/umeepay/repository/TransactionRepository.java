package vn.unicloud.umeepay.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.unicloud.umeepay.entity.common.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String>, JpaSpecificationExecutor<Transaction> {

    Transaction findByVirtualAccount(String virtualAccount);

    Transaction findByRefTransactionId(String refTransactionId);

}
