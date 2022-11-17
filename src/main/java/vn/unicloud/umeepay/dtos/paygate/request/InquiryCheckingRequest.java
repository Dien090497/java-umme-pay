package vn.unicloud.umeepay.dtos.paygate.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class InquiryCheckingRequest extends BaseRequestData {
    @NotEmpty
    private String virtualAccount;
}
