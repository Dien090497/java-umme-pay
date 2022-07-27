package vn.unicloud.vietqr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.unicloud.vietqr.entity.Transaction;

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
