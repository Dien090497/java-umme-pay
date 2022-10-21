package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.MerchantDto;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GetMerchantResponse extends BaseResponseData {

    private boolean success;

    private MerchantDto merchant;

}
