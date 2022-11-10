package vn.unicloud.umeepay.dtos.merchant.request;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.core.BaseRequestData;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class CmsBlockMerchantMemberRequest extends BaseRequestData {

    @NotNull
    private String memberId;

}
