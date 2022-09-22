package vn.unicloud.umeepay.dtos.vietqr.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.umeepay.core.BaseResponseData;

@Data
@AllArgsConstructor
public class ConfirmTransactionResponse extends BaseResponseData {

    private boolean status;

}
