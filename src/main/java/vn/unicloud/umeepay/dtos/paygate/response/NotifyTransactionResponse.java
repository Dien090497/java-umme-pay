package vn.unicloud.umeepay.dtos.paygate.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import vn.unicloud.umeepay.core.BaseResponseData;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class NotifyTransactionResponse extends BaseResponseData {

    private boolean status;

}
