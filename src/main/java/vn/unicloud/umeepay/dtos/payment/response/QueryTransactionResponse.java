package vn.unicloud.umeepay.dtos.payment.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import lombok.NoArgsConstructor;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.enums.TransactionStatus;

import java.time.LocalDateTime;

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
