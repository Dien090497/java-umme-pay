package vn.unicloud.vietqr.dtos.request;

import lombok.Data;
import vn.unicloud.vietqr.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateCallbackInfoRequest extends BaseRequestData {

    @NotEmpty
    private String callbackURL;
    @NotEmpty
    private String callbackCert;

}
