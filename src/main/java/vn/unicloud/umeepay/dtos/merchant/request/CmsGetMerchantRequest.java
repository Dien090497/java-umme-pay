package vn.unicloud.umeepay.dtos.merchant.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CmsGetMerchantRequest extends BaseRequestData {

    @NotNull
    private String id;

}
