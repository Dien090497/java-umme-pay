package vn.unicloud.umeepay.dtos.auth.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AdminRefreshTokenRequest extends BaseRequestData {

    @NotBlank
    private String refreshToken;

}
