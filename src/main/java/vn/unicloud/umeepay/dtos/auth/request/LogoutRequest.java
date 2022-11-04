package vn.unicloud.umeepay.dtos.auth.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.unicloud.umeepay.core.BaseRequestData;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogoutRequest extends BaseRequestData {

    private String refreshToken;

}