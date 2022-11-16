package vn.unicloud.umeepay.dtos.paylink.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.dtos.model.CustomerInfoForm;
import vn.unicloud.umeepay.enums.CustomerInfoType;
import vn.unicloud.umeepay.enums.PaymentExpireType;
import vn.unicloud.umeepay.enums.TransactionStatus;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPayLinkVietQRResponse extends BaseResponseData {

    private String shopName;

    private String merchantCode;

    private String merchantLogoUrl;

    private String qrContent;

    private Long amount;

    private String description;

    private String successUrl;

    private String failUrl;

    private Integer redirectAfter;

    private TransactionStatus status;

}
