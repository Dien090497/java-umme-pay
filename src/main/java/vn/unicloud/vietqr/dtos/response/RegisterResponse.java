package vn.unicloud.vietqr.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.vietqr.core.BaseResponseData;

@Data
@AllArgsConstructor
public class RegisterResponse extends BaseResponseData {

    private boolean status;

}
