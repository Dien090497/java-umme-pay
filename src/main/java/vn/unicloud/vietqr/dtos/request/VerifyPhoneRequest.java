package vn.unicloud.vietqr.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.vietqr.core.BaseRequestData;

@Data
@AllArgsConstructor
public class VerifyPhoneRequest extends BaseRequestData {

    private String phone;

}
