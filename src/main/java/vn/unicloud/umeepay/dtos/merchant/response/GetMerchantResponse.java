package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.MerchantDto;

@Data
@AllArgsConstructor
public class GetMerchantResponse extends BaseResponseData {

    private boolean success;

    private MerchantDto merchant;

}
