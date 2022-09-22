package vn.unicloud.umeepay.dtos.vietqr.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class RollbackTransactionRequest extends BaseRequestData {

    @NotEmpty
    private String transactionId;

    private String reasonDesc;

}
