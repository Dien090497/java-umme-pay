package vn.unicloud.umeepay.client.testapi.paygate.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class DepositCheckingClientRequest extends BaseRequestData {

    @NotBlank
    private String url;

    @NotEmpty
    private String virtualAccount;

    @NotNull
    @PositiveOrZero
    private Long amount;

}
