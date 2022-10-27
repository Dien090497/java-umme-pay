package vn.unicloud.umeepay.client.testapi.paygate.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.unicloud.umeepay.core.BaseResponseData;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotifyTransactionClientResponse extends BaseResponseData {

    private boolean status;

}
