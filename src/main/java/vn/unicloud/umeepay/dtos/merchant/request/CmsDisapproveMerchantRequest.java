package vn.unicloud.umeepay.dtos.merchant.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.common.TrimString;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CmsDisapproveMerchantRequest extends BaseRequestData {

    @JsonIgnore
    private String id;

    @Size(max = 250)
    @NotBlank
    @JsonDeserialize(using = TrimString.class)
    private String reason;

}
