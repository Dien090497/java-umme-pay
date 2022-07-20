package vn.unicloud.vietqr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.unicloud.vietqr.entity.Nickname;
import vn.unicloud.vietqr.entity.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    Transaction findFirstByVirtualAccount(String virtualAccount);

}
