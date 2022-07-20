package vn.unicloud.vietqr.dtos.request;

import lombok.Data;
import vn.unicloud.vietqr.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterRequest extends BaseRequestData {

    @NotEmpty
    private String fullName;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
