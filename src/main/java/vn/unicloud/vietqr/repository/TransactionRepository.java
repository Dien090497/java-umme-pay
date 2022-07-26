package vn.unicloud.vietqr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unicloud.vietqr.entity.Transaction;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    Transaction findFirstByVirtualAccount(String virtualAccount);

}
