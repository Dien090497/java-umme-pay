package vn.unicloud.umeepay.dtos.paygate.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class DepositCheckingRequest extends BaseRequestData {

    @NotEmpty
    private String virtualAccount;

    @NotNull
    @PositiveOrZero
    private Long amount;

}
