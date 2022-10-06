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
public class DepositCheckingResponse extends BaseResponseData {

    private String displayName;

    private String actualAccount;

    private Long amount;

    private boolean isSuccess;

}
