package vn.unicloud.vietqr.dtos.request;

import lombok.Data;
import vn.unicloud.vietqr.core.BaseRequestData;

@Data
public class LoginRequest extends BaseRequestData {
    private String username;

    private String password;
}
