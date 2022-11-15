package vn.unicloud.umeepay.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import vn.unicloud.umeepay.entity.Transaction;
import vn.unicloud.umeepay.enums.PaymentType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TransactionRepository extends JpaRepository<Transaction, String>, JpaSpecificationExecutor<Transaction> {

    Transaction findByVirtualAccount(String virtualAccount);

    Transaction findByRefTransactionId(String refTransactionId);

    @Query("SELECT COUNT (transaction.id)" +
            "FROM Transaction transaction " +
            "JOIN transaction.merchant merchant " +
            "WHERE merchant IS NOT NULL " +
            "      AND (?1 IS NULL OR merchant.id = ?1 ) " +
            "      AND (?2 IS NULL OR FUNCTION('date', transaction.completeTime) = ?2)")
    Long getTotalTransaction(String merchantId, LocalDate date);

    @Query("SELECT SUM(transaction.amount) " +
            "FROM Transaction transaction " +
            "JOIN transaction.merchant merchant " +
            "WHERE merchant IS NOT NULL " +
            "      AND (?1 IS NULL OR merchant.id = ?1 ) " +
            "      AND (?2 IS NULL OR FUNCTION('date', transaction.completeTime) = ?2) " +
            "GROUP BY merchant")
    Long getTotalTransactionAmount(String merchantId, LocalDate date);

    @Query("SELECT FUNCTION('date', transaction.completeTime) as date, " +
            "      transaction.paymentType as type, " +
            "       SUM (transaction.amount) as amount " +
            "FROM Transaction transaction " +
            "JOIN transaction.merchant merchant " +
            "WHERE merchant IS NOT NULL " +
            "      AND (?1 IS NULL OR merchant.id = ?1) " +
            "      AND (?2 IS NULL OR FUNCTION('date', transaction.completeTime) >= ?2) " +
            "      AND (?3 IS NULL OR FUNCTION('date', transaction.completeTime) <= ?3) " +
            "GROUP BY FUNCTION('date', transaction.completeTime) , transaction.paymentType")
    List<Map<String, Object>> getTransactionStatistics(String merchantId, LocalDate from, LocalDate to);
}
