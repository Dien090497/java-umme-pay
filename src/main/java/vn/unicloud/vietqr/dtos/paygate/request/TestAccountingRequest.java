package vn.unicloud.vietqr.dtos.paygate.request;

import lombok.Data;
import vn.unicloud.vietqr.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class TestAccountingRequest extends BaseRequestData {

    private String accountNo;

    private Long amount;

    private String terminalId;

}
