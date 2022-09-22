package vn.unicloud.umeepay.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

@Data
@AllArgsConstructor
public class VerifyPhoneRequest extends BaseRequestData {

    private String phone;

}
