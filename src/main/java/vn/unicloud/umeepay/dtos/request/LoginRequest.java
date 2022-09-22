package vn.unicloud.umeepay.dtos.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

@Data
public class LoginRequest extends BaseRequestData {
    private String username;

    private String password;
}
