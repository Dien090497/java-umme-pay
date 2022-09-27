package vn.unicloud.umeepay.dtos.payment.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CancelTransactionRequest extends BaseRequestData {

    @NotEmpty
    private String transactionId;

}
