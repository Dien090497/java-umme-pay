package vn.unicloud.vietqr.dtos.paygate.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.vietqr.core.BaseResponseData;

@Data
@AllArgsConstructor
public class NotifyTransactionResponse extends BaseResponseData {

    private boolean status;

}
