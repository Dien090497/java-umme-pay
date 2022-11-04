package vn.unicloud.umeepay.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseResponseData;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class VerifyOTPResponse extends BaseResponseData {

    private boolean status;

}
