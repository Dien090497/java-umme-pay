package vn.unicloud.umeepay.dtos.merchant.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class CreateMerchantRequest extends BaseRequestData {

    @NotBlank
    private String accountId;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

}
