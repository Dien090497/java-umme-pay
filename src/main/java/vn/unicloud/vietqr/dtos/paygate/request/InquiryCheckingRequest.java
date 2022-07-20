package vn.unicloud.vietqr.dtos.paygate.request;

import lombok.Data;
import vn.unicloud.vietqr.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class InquiryCheckingRequest extends BaseRequestData {
    @NotEmpty
    private String virtualAccount;
}
