package vn.unicloud.umeepay.dtos.payment.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.model.CustomerInfo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
public class QueryTransactionRequest extends BaseRequestData {

    @NotEmpty
    private String transactionId;

}
