package vn.unicloud.umeepay.client.testapi.transaction.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unicloud.umeepay.core.BaseResponseData;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelTransactionClientResponse extends BaseResponseData {

    private boolean success;

}
