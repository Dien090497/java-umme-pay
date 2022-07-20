package vn.unicloud.vietqr.model;

import lombok.Builder;
import lombok.Data;
import vn.unicloud.vietqr.enums.CallbackStatus;

@Data
@Builder
public class CallbackMessage {

    private int errorCode;
    private String errorDesc;
    private String transactionId;
    private String approvedCode;
    private String traceId;
    private CallbackStatus status;

}
