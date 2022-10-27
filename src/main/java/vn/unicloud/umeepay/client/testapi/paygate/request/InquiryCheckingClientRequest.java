package vn.unicloud.umeepay.client.testapi.paygate.request;

import lombok.Getter;
import lombok.Setter;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class InquiryCheckingClientRequest extends BaseRequestData {
    @NotBlank
    private String url;
    @NotEmpty
    private String virtualAccount;
}
