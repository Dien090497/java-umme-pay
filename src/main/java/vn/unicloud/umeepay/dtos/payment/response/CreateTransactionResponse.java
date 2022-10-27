package vn.unicloud.umeepay.dtos.payment.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTransactionResponse extends BaseResponseData {

    private String transactionId;

    private long timeout;

    private String url;

    private String virtualAccount;

    private String description;

    private long amount;

    private String qrCodeString;

    private LocalDateTime createdDateTime;

}
