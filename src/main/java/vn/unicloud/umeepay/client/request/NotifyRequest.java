package vn.unicloud.umeepay.client.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class NotifyRequest {

    private String transactionId;

    private String refTransactionId;

    private String virtualAccount;

    private String actualAccount;

    private String fromBin;

    private String fromAccount;

    private boolean success;

    private Long amount;

    private String statusCode;

    private String txnNumber;

    private String transferDesc;

    private LocalDateTime time;

}
