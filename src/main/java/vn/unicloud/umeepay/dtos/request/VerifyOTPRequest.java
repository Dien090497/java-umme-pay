package vn.unicloud.umeepay.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;

@Getter
@Setter
@ToString
public class VerifyOTPRequest extends BaseRequestData {

    private String phone;

    private String otp;

}
