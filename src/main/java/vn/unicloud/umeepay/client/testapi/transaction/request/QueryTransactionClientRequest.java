package vn.unicloud.umeepay.client.testapi.transaction.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class QueryTransactionClientRequest extends BaseRequestData {
    @NotEmpty
    private String transactionId;
}
