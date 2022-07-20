package vn.unicloud.vietqr.dtos.request;

import lombok.Data;
import vn.unicloud.vietqr.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class ClientLoginRequest extends BaseRequestData {
    @NotEmpty
    private String clientId;

    @NotEmpty
    private String secretKey;

}
