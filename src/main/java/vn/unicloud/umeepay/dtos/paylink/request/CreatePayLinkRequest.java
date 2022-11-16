package vn.unicloud.umeepay.dtos.paylink.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;
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
public class CreatePayLinkRequest extends BaseRequestData {

    @NotNull
    private Long amount;

    private String refTransactionId;

    private String bankAccountId;

    private PaymentExpireType expireType;

    @NotBlank
    private String description;

    private String fileUrl;

    @NotNull
    private CustomerInfoType customerInfoType;

    private CustomerInfoForm customerInfoForm;

    private String successUrl;

    private String failUrl;

    private Integer redirectAfter;

}
