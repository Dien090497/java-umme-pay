package vn.unicloud.umeepay.client.testapi.paygate.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class NotifyTransactionClientRequest extends BaseRequestData {

    @NotBlank
    private String url;

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
