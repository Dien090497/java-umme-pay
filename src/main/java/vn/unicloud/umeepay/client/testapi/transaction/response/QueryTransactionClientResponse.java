package vn.unicloud.umeepay.client.testapi.transaction.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.enums.TransactionStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryTransactionClientResponse extends BaseResponseData {
    private TransactionStatus status;

    private String refTransactionId;

    private Long amount;

}
