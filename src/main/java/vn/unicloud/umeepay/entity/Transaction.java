package vn.unicloud.umeepay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.GenericGenerator;
import vn.unicloud.umeepay.enums.Branch;
import vn.unicloud.umeepay.enums.PaymentType;
import vn.unicloud.umeepay.enums.TransactionStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = Transaction.COLLECTION_NAME)
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class Transaction {

    public static final String COLLECTION_NAME = "transaction";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    @JsonIgnore
    private Merchant merchant;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "ref_transaction_id", nullable = false, unique = true)
    private String refTransactionId;

    private String callbackUrl;

    private String callbackResponseCode;

    private LocalDateTime createDateTime;

    private LocalDateTime depositTime;

    private LocalDateTime completeTime;

    private String description;

    @Column(name = "account_no", nullable = false)
    private String accountNo;

    private String accountName;

    @Column(name = "virtual_account", nullable = false)
    private String virtualAccount;

    private String fromBin;

    private String fromAccount;

    private String txnNumber;

    private long timestamp;

    private long timeout;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private String successUrl;

    private String failUrl;

    private Integer callbackAfter;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank_type", nullable = false)
    private Branch bankType;

}
