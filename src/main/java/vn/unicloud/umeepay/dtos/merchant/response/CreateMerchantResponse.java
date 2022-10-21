package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.MerchantDto;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CreateMerchantResponse extends BaseResponseData {

    private boolean success;

    private MerchantDto merchant;

}
