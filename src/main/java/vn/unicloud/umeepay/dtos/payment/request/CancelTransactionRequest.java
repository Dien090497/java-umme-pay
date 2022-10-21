package vn.unicloud.umeepay.dtos.payment.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
public class CancelTransactionRequest extends BaseRequestData {

    @NotEmpty
    private String transactionId;

}
