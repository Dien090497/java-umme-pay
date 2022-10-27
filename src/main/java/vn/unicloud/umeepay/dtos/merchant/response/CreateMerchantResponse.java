package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.MerchantDto;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CreateMerchantResponse extends BaseResponseData {

    private boolean success;

    private MerchantDto merchant;

}
