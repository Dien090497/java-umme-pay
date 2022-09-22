package vn.unicloud.umeepay.dtos.paygate.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.umeepay.core.BaseResponseData;

@Data
@AllArgsConstructor
public class DepositCheckingResponse extends BaseResponseData {

    private String displayName;

    private String actualAccount;

    private Long amount;

    private boolean isSuccess;

}
