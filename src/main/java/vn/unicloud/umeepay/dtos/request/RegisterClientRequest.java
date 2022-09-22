package vn.unicloud.umeepay.dtos.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterClientRequest extends BaseRequestData {
    @NotEmpty
    private String branchCode;

    @NotEmpty
    private String desc;

    private String callbackURL;

    private String callbackCert;

}
