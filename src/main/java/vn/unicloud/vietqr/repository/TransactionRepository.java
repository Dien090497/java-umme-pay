package vn.unicloud.vietqr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.unicloud.vietqr.entity.Transaction;
import vn.unicloud.vietqr.enums.TransactionStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    Transaction findFirstByVirtualAccount(String virtualAccount);

    @Query("select t from Transaction t where " +
        "(:keyword IS NULL OR (t.customerPhone LIKE %:keyword% OR t.customerIdCardNo LIKE %:keyword% OR t.terminalId LIKE %:keyword%))")
    Page<Transaction> findAllByFilterKeyword(Pageable pageable, @Param("keyword") String keyword);

    @Query("select t from Transaction t where " +
        "(:keyword IS NULL OR (t.customerPhone LIKE %:keyword% OR t.customerIdCardNo LIKE %:keyword% OR t.terminalId LIKE %:keyword%))")
    List<Transaction> findAllByFilterKeyword(@Param("keyword") String keyword);

    @Query("select t from Transaction t where " +
        "(:keyword IS NULL OR (t.customerPhone LIKE %:keyword% OR t.customerIdCardNo LIKE %:keyword% OR t.terminalId LIKE %:keyword%)) AND " +
        "(:traceId IS NULL OR (t.traceId = :traceId)) AND " +
        "(:status IS NULL OR (t.status = :SUCCESS)) AND " +
        "(:terminalId IS NULL OR (t.terminalId = :terminalId)) AND " +
        "((:fromDate IS NULL OR :toDate IS NULL) OR (t.createDate BETWEEN :fromDate AND :toDate))")
    Page<Transaction> findAllBySuccess(Pageable pageable,
                                             @Param("keyword") String keyword,
                                             @Param("traceId") String traceId,
                                             @Param("terminalId") String terminalId,
                                             @Param("status") TransactionStatus status,
                                             @Param("fromDate") LocalDate fromDate,
                                             @Param("toDate") LocalDate toDate,
                                             @Param("SUCCESS") TransactionStatus successTerm
    );

    @Query("select t from Transaction t where " +
        "(:keyword IS NULL OR (t.customerPhone LIKE %:keyword% OR t.customerIdCardNo LIKE %:keyword% OR t.terminalId LIKE %:keyword%)) AND " +
        "(:traceId IS NULL OR (t.traceId = :traceId)) AND " +
        "(:status IS NULL OR (t.status <> :SUCCESS)) AND " +
        "(:terminalId IS NULL OR (t.terminalId = :terminalId)) AND " +
        "((:fromDate IS NULL OR :toDate IS NULL) OR (t.createDate BETWEEN :fromDate AND :toDate))")
    Page<Transaction> findAllByFail(Pageable pageable,
                                       @Param("keyword") String keyword,
                                       @Param("traceId") String traceId,
                                       @Param("terminalId") String terminalId,
                                       @Param("status") TransactionStatus status,
                                       @Param("fromDate") LocalDate fromDate,
                                       @Param("toDate") LocalDate toDate,
                                       @Param("SUCCESS") TransactionStatus successTerm
    );

    @Query("select t from Transaction t where " +
        "(:keyword IS NULL OR (t.customerPhone LIKE %:keyword% OR t.customerIdCardNo LIKE %:keyword% OR t.terminalId LIKE %:keyword%)) AND " +
        "(:traceId IS NULL OR (t.traceId = :traceId)) AND " +
        "(:status IS NULL OR ((t.status = :status AND t.status = :SUCCESS) OR (:status = :FAIL AND t.status <> :SUCCESS))) AND " +
        "(:terminalId IS NULL OR (t.terminalId = :terminalId)) AND " +
        "((:fromDate IS NULL OR :toDate IS NULL) OR (t.createDate BETWEEN :fromDate AND :toDate))")
    List<Transaction> findAllByFilterKeyword(@Param("keyword") String keyword,
                                             @Param("traceId") String traceId,
                                             @Param("terminalId") String terminalId,
                                             @Param("status") TransactionStatus status,
                                             @Param("fromDate") LocalDate fromDate,
                                             @Param("toDate") LocalDate toDate,
                                             @Param("SUCCESS") TransactionStatus successTerm,
                                             @Param("FAIL") TransactionStatus failTerm
    );

    @Query("select t from Transaction t where " +
        "(:keyword IS NULL OR (t.customerPhone LIKE %:keyword% OR t.customerIdCardNo LIKE %:keyword% OR t.terminalId LIKE %:keyword%)) AND " +
        "(:traceId IS NULL OR (t.traceId = :traceId)) AND " +
        "(:status IS NULL OR (t.status = :SUCCESS)) AND " +
        "(:terminalId IS NULL OR (t.terminalId = :terminalId)) AND " +
        "((:fromDate IS NULL OR :toDate IS NULL) OR (t.createDate BETWEEN :fromDate AND :toDate))")
    List<Transaction> findAllBySuccess(@Param("keyword") String keyword,
                                             @Param("traceId") String traceId,
                                             @Param("terminalId") String terminalId,
                                             @Param("status") TransactionStatus status,
                                             @Param("fromDate") LocalDate fromDate,
                                             @Param("toDate") LocalDate toDate,
                                             @Param("SUCCESS") TransactionStatus successTerm
    );

    @Query("select t from Transaction t where " +
        "(:keyword IS NULL OR (t.customerPhone LIKE %:keyword% OR t.customerIdCardNo LIKE %:keyword% OR t.terminalId LIKE %:keyword%)) AND " +
        "(:traceId IS NULL OR (t.traceId = :traceId)) AND " +
        "(:status IS NULL OR (t.status <> :SUCCESS)) AND " +
        "(:terminalId IS NULL OR (t.terminalId = :terminalId)) AND " +
        "((:fromDate IS NULL OR :toDate IS NULL) OR (t.createDate BETWEEN :fromDate AND :toDate))")
    List<Transaction> findAllByFail(@Param("keyword") String keyword,
                                             @Param("traceId") String traceId,
                                             @Param("terminalId") String terminalId,
                                             @Param("status") TransactionStatus status,
                                             @Param("fromDate") LocalDate fromDate,
                                             @Param("toDate") LocalDate toDate,
                                             @Param("SUCCESS") TransactionStatus successTerm
    );

//    @Query("select t from Transaction t where " +
//        "(:keyword IS NULL OR (t.customerPhone LIKE %:keyword% OR t.customerIdCardNo LIKE %:keyword% OR t.terminalId LIKE %:keyword%)) AND " +
//        "(:traceId IS NULL OR (t.traceId = :traceId)) AND " +
//        "(:status IS NULL OR (t.status = :status))")
//    Page<Transaction> findAllByFilterKeyword(Pageable pageable,
//                                             @Param("keyword") String keyword,
//                                             @Param("traceId") String traceId,
//                                             @Param("status") String status,
//                                             @Param("fromDate") String fromDate,
//                                             @Param("toDate") String toDate
//    );

}
