package vn.unicloud.umeepay.dtos.transaction.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.entity.Transaction;
import vn.unicloud.umeepay.enums.TransactionStatus;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
public class GetTransactionsRequest extends BaseRequestData {
    private Pageable pageable;
    private String keyword;
    private TransactionStatus status;
    private String fromDate;
    private String toDate;
    private String transactionId;


    public Specification<Transaction> getSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if (fromDate != null) {
                LocalDateTime dateTime = LocalDateTime.of(LocalDate.parse(fromDate, formatter) , LocalTime.MIN);
//                start < field < end
                predicates.add(cb.greaterThan(root.get(Transaction.Fields.createDateTime), dateTime));
            }
            if (toDate != null) {
                LocalDateTime dateTime = LocalDateTime.of(LocalDate.parse(toDate, formatter) , LocalTime.MIN);
                predicates.add(cb.lessThan(root.get(Transaction.Fields.createDateTime), dateTime));
            }
            if (status != null) {
                predicates.add(cb.equal((root.get(Transaction.Fields.status)), status));
            }
            if (keyword != null) {
                predicates.add(cb.like(cb.lower(root.get(Transaction.Fields.description)), "%" + keyword.toLowerCase() + "%"));
            }
            if (transactionId != null) {
                predicates.add(cb.equal(root.get(Transaction.Fields.id), transactionId));
            }

//            if(merchantId != null) {
//                Join<Transaction, Merchant> joinMerchant = root.join("merchant", JoinType.INNER);
//                predicates.add(cb.equal(joinMerchant.get("merchant_id"), merchantId));
//            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}

