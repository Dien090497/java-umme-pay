package vn.unicloud.umeepay.dtos.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateCallbackInfoRequest extends BaseRequestData {

    @NotEmpty
    private String callbackURL;
    @NotEmpty
    private String callbackCert;

}
