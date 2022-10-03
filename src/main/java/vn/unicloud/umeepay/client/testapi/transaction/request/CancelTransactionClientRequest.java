package vn.unicloud.umeepay.client.testapi.transaction.request;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreType;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class CancelTransactionClientRequest extends BaseRequestData {
    @NotBlank
    private String url;
    @NotEmpty
    private String transactionId;
}
