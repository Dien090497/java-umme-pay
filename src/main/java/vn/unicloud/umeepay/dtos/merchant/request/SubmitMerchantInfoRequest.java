package vn.unicloud.umeepay.dtos.merchant.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.dtos.model.EnterpriseMerchantDto;
import vn.unicloud.umeepay.dtos.model.PersonalMerchantDto;
import vn.unicloud.umeepay.enums.BusinessProduct;
import vn.unicloud.umeepay.enums.BusinessType;
import vn.unicloud.umeepay.enums.RevenueType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class SubmitMerchantInfoRequest extends BaseRequestData {

    @NotBlank
    private String name;

    @NotNull
    private BusinessType businessType;

    private List<BusinessProduct> businessProducts;

    @NotBlank
    private String product;

    @NotNull
    private Long maxPrice;

    @NotNull
    private RevenueType revenueType;

    private String website;

    @NotBlank
    private String address;

    @NotBlank
    private String email;

    private PersonalMerchantDto personalMerchant;

    private EnterpriseMerchantDto enterpriseMerchant;

}

