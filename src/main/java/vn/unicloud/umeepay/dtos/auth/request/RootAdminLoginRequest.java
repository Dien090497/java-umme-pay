package vn.unicloud.umeepay.dtos.auth.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RootAdminLoginRequest extends BaseRequestData {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
