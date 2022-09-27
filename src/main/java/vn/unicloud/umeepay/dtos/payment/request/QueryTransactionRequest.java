package vn.unicloud.umeepay.dtos.payment.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.model.CustomerInfo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
public class QueryTransactionRequest extends BaseRequestData {

    @NotEmpty
    private String transactionId;

}
