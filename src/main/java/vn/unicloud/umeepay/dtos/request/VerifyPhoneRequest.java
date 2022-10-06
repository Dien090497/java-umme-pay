package vn.unicloud.umeepay.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import vn.unicloud.umeepay.core.BaseRequestData;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class VerifyPhoneRequest extends BaseRequestData {

    private String phone;

}
