package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.umeepay.core.BaseResponseData;

@Data
@AllArgsConstructor
public class UpdateMerchantResponse extends BaseResponseData {

    private boolean success;

}
