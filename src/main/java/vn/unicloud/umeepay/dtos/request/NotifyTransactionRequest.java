package vn.unicloud.umeepay.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class NotifyTransactionRequest extends BaseRequestData {
    @NotEmpty
    private String iNick;

    private String transactionId;

    private String amount;

    private String traceId;

    private String statusCode;

    private String desc;
}
