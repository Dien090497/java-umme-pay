package vn.unicloud.vietqr.dtos.vietqr.request;

import lombok.Data;
import vn.unicloud.vietqr.core.BaseRequestData;

import javax.validation.constraints.*;

@Data
public class CreateTransactionRequest extends BaseRequestData {
    @NotEmpty
    private String terminalId;

    @NotEmpty
    private String terminalLocation;

    @NotEmpty
    private String customerName;

    @NotEmpty
    private String customerPhone;

    @NotEmpty
    private String customerIdNumber;

    @Positive
    @NotNull
    private Long amount;

    private boolean printReceipt = false;

}
