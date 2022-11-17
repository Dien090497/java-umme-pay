package vn.unicloud.umeepay.dtos.paylink.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.dtos.model.CustomerInfoForm;
import vn.unicloud.umeepay.enums.CustomerInfoType;
import vn.unicloud.umeepay.enums.TransactionStatus;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PayLinkResponse extends BaseResponseData {

    private String payLinkCode;

    private String payLinkUrl;

    private String qrCode;

    private CustomerDto customerDto;

    private TransactionStatus status;

    private CustomerInfoType customerInfoType;

    private CustomerInfoForm customerInfoForm;

}
