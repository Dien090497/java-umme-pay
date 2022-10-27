package vn.unicloud.umeepay.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
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
