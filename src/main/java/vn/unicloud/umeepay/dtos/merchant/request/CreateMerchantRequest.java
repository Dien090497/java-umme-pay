package vn.unicloud.umeepay.dtos.merchant.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateMerchantRequest extends BaseRequestData {

    private String merchantName;

    private String accountNo;

}
