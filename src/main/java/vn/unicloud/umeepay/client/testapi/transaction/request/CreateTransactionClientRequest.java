package vn.unicloud.umeepay.client.testapi.transaction.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.model.CustomerInfo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
public class CreateTransactionClientRequest extends BaseRequestData {

    @NotEmpty
    private String refTransactionId;

    @Positive
    private Long amount;

    @NotEmpty
    private String description;

    @PositiveOrZero
    private Long timeout;

    private String title;

    private String language;

    private CustomerInfo customerInfo;
}
