package vn.unicloud.umeepay.dtos.merchant.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.enums.Branch;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CheckBankAccountRequest extends BaseRequestData {

    @NotNull
    private Branch branch;

    @NotBlank
    private String idCardNo;

    @NotBlank
    private String accountNo;

}

