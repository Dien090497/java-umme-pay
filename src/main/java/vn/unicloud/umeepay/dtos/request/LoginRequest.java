package vn.unicloud.umeepay.dtos.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest extends BaseRequestData {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
