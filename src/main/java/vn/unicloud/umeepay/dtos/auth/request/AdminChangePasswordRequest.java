package vn.unicloud.umeepay.dtos.auth.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AdminChangePasswordRequest extends BaseRequestData {

    @NotEmpty
    private String currentPassword;

    @NotEmpty
    private String newPassword;

}
