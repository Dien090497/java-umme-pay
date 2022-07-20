package vn.unicloud.vietqr.dtos.vietqr.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import vn.unicloud.vietqr.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class ConfirmTransactionRequest extends BaseRequestData {

    @NotEmpty
    private String transactionId;

    @Schema(defaultValue = "0")
    private int numNote50;

    @Schema(defaultValue = "0")
    private int numNote100;

    @Schema(defaultValue = "0")
    private int numNote200;

    @Schema(defaultValue = "0")
    private int numNote500;

}
