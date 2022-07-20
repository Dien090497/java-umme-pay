package vn.unicloud.vietqr.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.vietqr.core.BaseResponseData;

@Data
@AllArgsConstructor
public class GetAccountInfoResponse extends BaseResponseData {

    private String accountNo;

    private String desc;

}
