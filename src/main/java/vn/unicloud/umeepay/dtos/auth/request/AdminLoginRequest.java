package vn.unicloud.umeepay.dtos.auth.request;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.core.BaseRequestData;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginRequest extends BaseRequestData {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
