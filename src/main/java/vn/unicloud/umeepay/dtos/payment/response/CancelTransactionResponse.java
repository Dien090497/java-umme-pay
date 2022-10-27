package vn.unicloud.umeepay.dtos.payment.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CancelTransactionResponse extends BaseResponseData {

    private boolean success;

}
