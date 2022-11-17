package vn.unicloud.umeepay.dtos.paylink.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.dtos.model.CustomerFillDto;
import vn.unicloud.umeepay.dtos.model.CustomerInfoForm;
import vn.unicloud.umeepay.enums.CustomerInfoType;
import vn.unicloud.umeepay.enums.PaymentExpireType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerInfoRequest extends BaseRequestData {

    @NotBlank
    private String payLinkCode;

    @NotNull
    private CustomerFillDto customer;

}
