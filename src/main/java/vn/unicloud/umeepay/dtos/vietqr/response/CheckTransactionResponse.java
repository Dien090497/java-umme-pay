package vn.unicloud.umeepay.dtos.vietqr.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.umeepay.core.BaseResponseData;

@Data
@AllArgsConstructor
public class CheckTransactionResponse extends BaseResponseData {

    private String transactionId;

    private boolean success;

    private String traceId;

}
