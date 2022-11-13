package vn.unicloud.umeepay.dtos.model;

import lombok.*;
import vn.unicloud.umeepay.entity.Credential;
import vn.unicloud.umeepay.enums.BusinessProduct;
import vn.unicloud.umeepay.enums.MerchantStatus;
import vn.unicloud.umeepay.enums.RevenueType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PersonalMerchantDto {

    @NotBlank
    private String companyName;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    @NotBlank
    private String frontUrl;

    @NotBlank
    private String backUrl;

}
