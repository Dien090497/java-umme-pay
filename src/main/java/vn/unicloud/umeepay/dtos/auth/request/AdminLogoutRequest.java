package vn.unicloud.umeepay.dtos.auth.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AdminLogoutRequest extends BaseRequestData {

    @NotNull
    private String refreshToken;

}
