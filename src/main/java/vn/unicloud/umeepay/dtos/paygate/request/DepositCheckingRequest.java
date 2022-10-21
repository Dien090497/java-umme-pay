package vn.unicloud.umeepay.dtos.paygate.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
public class DepositCheckingRequest extends BaseRequestData {

    @NotEmpty
    private String virtualAccount;

    @NotNull
    @PositiveOrZero
    private Long amount;

}
