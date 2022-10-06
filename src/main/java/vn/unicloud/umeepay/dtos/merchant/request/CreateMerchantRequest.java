package vn.unicloud.umeepay.dtos.merchant.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class CreateMerchantRequest extends BaseRequestData {

    private String merchantName;

    private String accountNo;

}
