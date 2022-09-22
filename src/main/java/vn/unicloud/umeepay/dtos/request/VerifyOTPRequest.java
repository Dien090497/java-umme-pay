package vn.unicloud.umeepay.dtos.request;

import lombok.Data;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;

@Data
@ToString
public class VerifyOTPRequest extends BaseRequestData {

    private String phone;

    private String otp;

}
