package vn.unicloud.umeepay.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class RegisterClientRequest extends BaseRequestData {
    @NotEmpty
    private String branchCode;

    @NotEmpty
    private String desc;

    private String callbackURL;

    private String callbackCert;

}
