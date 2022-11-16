package vn.unicloud.umeepay.dtos.paylink.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.enums.TransactionStatus;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayLinkStatusResponse extends BaseResponseData {

    private String payLinkCode;

    private TransactionStatus status;

}
