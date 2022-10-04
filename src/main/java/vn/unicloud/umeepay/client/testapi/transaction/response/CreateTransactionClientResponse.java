package vn.unicloud.umeepay.client.testapi.transaction.response;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseResponseData;

import java.time.LocalDateTime;

@Data
public class CreateTransactionClientResponse extends BaseResponseData {
    private String transactionId;

    private long timeout;

    private String url;

    private String virtualAccount;

    private String description;

    private long amount;

    private String qrCodeString;


    private LocalDateTime createdDateTime;

}
