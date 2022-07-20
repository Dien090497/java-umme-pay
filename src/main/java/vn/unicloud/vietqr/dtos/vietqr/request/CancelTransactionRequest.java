package vn.unicloud.vietqr.dtos.vietqr.request;

import lombok.Data;
import vn.unicloud.vietqr.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class CancelTransactionRequest extends BaseRequestData {

    @NotEmpty
    private String transactionId;

}
