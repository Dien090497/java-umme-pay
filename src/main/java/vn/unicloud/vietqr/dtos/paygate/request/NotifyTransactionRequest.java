package vn.unicloud.vietqr.dtos.paygate.request;

import lombok.Data;
import vn.unicloud.vietqr.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class NotifyTransactionRequest extends BaseRequestData {
    @NotEmpty
    private String cert;

    @NotEmpty
    private String virtualAccount;

    private boolean success = false;

    private String amount;

    private String traceId;

    private String statusCode;

    private String approveCode;

    private String desc;

    private long timestamp;
}
