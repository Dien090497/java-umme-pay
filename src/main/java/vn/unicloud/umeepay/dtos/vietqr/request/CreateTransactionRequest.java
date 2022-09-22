package vn.unicloud.umeepay.dtos.vietqr.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.*;

@Data
public class CreateTransactionRequest extends BaseRequestData {
    @NotEmpty
    private String terminalId;

    @NotEmpty
    private String terminalLocation;

    @NotEmpty
    private String customerPhone;

    @NotEmpty
    private String customerIdNumber;

    @Positive
    @NotNull
    private Long amount;

    private boolean printReceipt = false;

}
