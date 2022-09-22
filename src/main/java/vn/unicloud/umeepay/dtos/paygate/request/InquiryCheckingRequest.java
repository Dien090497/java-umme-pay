package vn.unicloud.umeepay.dtos.paygate.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class InquiryCheckingRequest extends BaseRequestData {
    @NotEmpty
    private String virtualAccount;
}
