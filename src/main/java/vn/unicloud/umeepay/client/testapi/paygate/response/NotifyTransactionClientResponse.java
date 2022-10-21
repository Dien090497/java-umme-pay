package vn.unicloud.umeepay.client.testapi.paygate.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unicloud.umeepay.core.BaseResponseData;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifyTransactionClientResponse extends BaseResponseData {

    private boolean status;

}
