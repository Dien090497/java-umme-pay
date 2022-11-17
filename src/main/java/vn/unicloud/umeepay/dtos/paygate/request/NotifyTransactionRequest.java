package vn.unicloud.umeepay.dtos.paygate.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
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
