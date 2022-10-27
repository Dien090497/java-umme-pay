package vn.unicloud.umeepay.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class UpdateCallbackInfoRequest extends BaseRequestData {

    @NotEmpty
    private String callbackURL;
    @NotEmpty
    private String callbackCert;

}
