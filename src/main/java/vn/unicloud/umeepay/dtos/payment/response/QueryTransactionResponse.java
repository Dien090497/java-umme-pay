package vn.unicloud.umeepay.dtos.payment.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.enums.TransactionStatus;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryTransactionResponse extends BaseResponseData {

    private TransactionStatus status;

    private String refTransactionId;

    private Long amount;

}
