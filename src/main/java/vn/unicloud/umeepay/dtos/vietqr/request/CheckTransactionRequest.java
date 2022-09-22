package vn.unicloud.umeepay.dtos.vietqr.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class CheckTransactionRequest extends BaseRequestData {

    @NotEmpty
    private String transactionId;

}
