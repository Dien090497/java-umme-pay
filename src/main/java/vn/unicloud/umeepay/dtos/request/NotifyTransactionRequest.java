package vn.unicloud.umeepay.dtos.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class NotifyTransactionRequest extends BaseRequestData {
    @NotEmpty
    private String iNick;

    private String transactionId;

    private String amount;

    private String traceId;

    private String statusCode;

    private String desc;
}
