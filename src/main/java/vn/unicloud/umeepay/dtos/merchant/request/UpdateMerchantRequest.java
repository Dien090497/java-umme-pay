package vn.unicloud.umeepay.dtos.merchant.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

@Data
public class UpdateMerchantRequest extends BaseRequestData {

    private String merchantName;

    private String accountNo;

}
