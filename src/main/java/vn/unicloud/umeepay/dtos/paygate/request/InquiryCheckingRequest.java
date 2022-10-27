package vn.unicloud.umeepay.dtos.paygate.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class InquiryCheckingRequest extends BaseRequestData {
    @NotEmpty
    private String virtualAccount;
}
