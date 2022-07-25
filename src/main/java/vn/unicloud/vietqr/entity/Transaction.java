package vn.unicloud.vietqr.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import vn.unicloud.vietqr.dtos.model.DispensedNotes;
import vn.unicloud.vietqr.enums.TransactionStatus;

import java.time.LocalDateTime;

@Document(collection = Transaction.COLLECTION_NAME)
@Data
@Builder
public class Transaction {

    public static final String COLLECTION_NAME = "transaction";

    @Id
    private String id;

    @Field("status")
    private TransactionStatus status;

    @Field("terminal_id")
    private String terminalId;

    @Field("bin")
    private String bin;

    @Field("terminal_location")
    private String terminalLocation;

    @Field("amount")
    private Long amount;

    @Field("virtual_account")
    private String virtualAccount;

    @Field("customer_id_card_no")
    private String customerIdCardNo;

    @Field("customer_phone")
    private String customerPhone;

    @Field("dispensed_notes")
    private DispensedNotes dispensedNotes;

    @Field("is_print_receipt")
    private boolean isPrintReceipt;

    @Field("trace_id")
    private String traceId;

    @Field("dispensed_error_code")
    private int dispensedErrorCode;

    @Field("dispensed_error_desc")
    private String dispensedErrorDesc;

    @Field("callback_error_code")
    private String callbackErrorCode;

    @Field("callback_error_desc")
    private String callbackErrorDesc;

    @Field("create_date")
    private LocalDateTime createDate;

    @Field("timestamp")
    private long timestamp;

}
