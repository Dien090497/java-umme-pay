package vn.unicloud.umeepay.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.enums.CallbackStatus;

@Getter
@Setter
@ToString
@Builder
public class CallbackMessage {

    private int errorCode;
    private String errorDesc;
    private String transactionId;
    private String approvedCode;
    private String traceId;
    private CallbackStatus status;

}
