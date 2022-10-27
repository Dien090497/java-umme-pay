package vn.unicloud.umeepay.dtos.payment.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.model.CustomerInfo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
public class CreateTransactionRequest extends BaseRequestData {

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
