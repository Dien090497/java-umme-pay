package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.MerchantDto;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class CreateMerchantResponse extends BaseResponseData {

    private boolean success;

    private MerchantDto merchant;

}
