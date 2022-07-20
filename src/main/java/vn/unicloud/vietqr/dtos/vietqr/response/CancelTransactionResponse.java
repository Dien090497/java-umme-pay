package vn.unicloud.vietqr.dtos.vietqr.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.vietqr.core.BaseResponseData;

@Data
@AllArgsConstructor
public class CancelTransactionResponse extends BaseResponseData {

    private boolean success;

}
