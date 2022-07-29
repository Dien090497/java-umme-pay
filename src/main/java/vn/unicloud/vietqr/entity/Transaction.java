package vn.unicloud.vietqr.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import vn.unicloud.vietqr.dtos.model.DispensedNotes;
import vn.unicloud.vietqr.enums.TransactionStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    public static final String COLLECTION_NAME = "transaction";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false,
        columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "status")
//    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(name = "terminal_id")
    private String terminalId;

    @Column(name = "bin")
    private String bin;

    @Column(name = "terminal_location")
    private String terminalLocation;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "virtual_account")
    private String virtualAccount;

    @Column(name = "actual_account")
    private String actualAccount;

    @Column(name = "customer_id_card_no")
    private String customerIdCardNo;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "dispensed_50_notes")
    private int dispensed50Notes;

    @Column(name = "dispensed_100_notes")
    private int dispensed100Notes;

    @Column(name = "dispensed_200_notes")
    private int dispensed200Notes;

    @Column(name = "dispensed_500_notes")
    private int dispensed500Notes;

    @Column(name = "is_print_receipt")
    private boolean isPrintReceipt;

    @Column(name = "trace_id")
    private String traceId;

    @Column(name = "dispensed_error_code")
    private int dispensedErrorCode;

    @Column(name = "dispensed_error_desc")
    private String dispensedErrorDesc;

    @Column(name = "callback_error_code")
    private String callbackErrorCode;

    @Column(name = "callback_error_desc")
    private String callbackErrorDesc;

    @Column(name = "create_date_time")
    private LocalDateTime createDateTime;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "timestamp")
    private long timestamp;

}
