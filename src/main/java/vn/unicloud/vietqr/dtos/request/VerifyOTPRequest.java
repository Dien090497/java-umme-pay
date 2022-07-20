package vn.unicloud.vietqr.dtos.request;

import lombok.Data;
import lombok.ToString;
import vn.unicloud.vietqr.core.BaseRequestData;

@Data
@ToString
public class VerifyOTPRequest extends BaseRequestData {

    private String phone;

    private String otp;

}
