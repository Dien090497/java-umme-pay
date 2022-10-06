package vn.unicloud.umeepay.dtos.user.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest extends BaseRequestData {

    private String fullName;

    @NotEmpty(message = "Password may not be empty")
    private String password;

    @NotEmpty(message = "Email may be not empty")
    private String email;

    @NotEmpty(message = "Phone may be not empty")
    private String phone;
}
