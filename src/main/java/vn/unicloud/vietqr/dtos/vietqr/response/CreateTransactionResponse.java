package vn.unicloud.vietqr.dtos.vietqr.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.vietqr.core.BaseResponseData;

@Data
@AllArgsConstructor
public class CreateTransactionResponse extends BaseResponseData {

    private String transactionId;

    private int timeout;

    private String qrCode;

    private String normalizedTerminalLocation;

    private String virtualAccount;

}
