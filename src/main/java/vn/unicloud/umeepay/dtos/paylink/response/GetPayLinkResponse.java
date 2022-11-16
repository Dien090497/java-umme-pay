package vn.unicloud.umeepay.dtos.paylink.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.dtos.model.CustomerInfoForm;
import vn.unicloud.umeepay.enums.CustomerInfoType;
import vn.unicloud.umeepay.enums.PaymentExpireType;
import vn.unicloud.umeepay.enums.TransactionStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetPayLinkResponse extends BaseResponseData {

    private Long amount;

    private String refTransactionId;

    private String bankAccountId;

    private PaymentExpireType expireType;

    private String description;

    private String fileUrl;

    private CustomerInfoType customerInfoType;

    private CustomerInfoForm customerInfoForm;

    private String successUrl;

    private String failUrl;

    private Integer redirectAfter;

    private TransactionStatus status;

}
