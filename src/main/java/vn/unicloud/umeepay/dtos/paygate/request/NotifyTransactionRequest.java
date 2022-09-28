package vn.unicloud.umeepay.dtos.paygate.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class NotifyTransactionRequest extends BaseRequestData {

    @NotBlank
    private String virtualAccount;

    private String fromBin;

    private String fromAccount;

    private boolean success;

    private Long amount;

    private String statusCode;

//    @NotBlank
    private String txnNumber;

    private String transferDesc;

    private LocalDateTime time;

}
