package vn.unicloud.umeepay.dtos.merchant.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;

@Getter
@Setter
@ToString
public class UpdateMerchantRequest extends BaseRequestData {

    private String merchantName;

    private String accountNo;

}
