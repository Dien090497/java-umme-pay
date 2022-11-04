package vn.unicloud.umeepay.dtos.payment.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class QueryTransactionRequest extends BaseRequestData {

    @NotEmpty
    private String transactionId;

}
