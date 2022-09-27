package vn.unicloud.umeepay.dtos.paygate.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class NotifyTransactionRequest extends BaseRequestData {

    @NotEmpty
    private String virtualAccount;

    private String transactionId;

    private String actualAccount;

    private String fromBin;

    private String fromAccount;

    private boolean success;

    private Long amount;

    private String statusCode;

    private String txnNumber;

    private String transferDesc;

    private LocalDateTime time;

}
