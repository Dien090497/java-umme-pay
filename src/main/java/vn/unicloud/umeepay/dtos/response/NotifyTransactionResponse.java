package vn.unicloud.umeepay.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.umeepay.core.BaseResponseData;

@Data
@AllArgsConstructor
public class NotifyTransactionResponse extends BaseResponseData {

    private String status;

}
