package vn.unicloud.umeepay.dtos.paylink.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.enums.TransactionStatus;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPayLinkBankTransferResponse extends BaseResponseData {

    private String shopName;

    private String merchantCode;

    private String merchantLogoUrl;

    private Long amount;

    private String description;

    private String bankName;

    private String accountNo;

    private String accountName;

    private String content;

    private String successUrl;

    private String failUrl;

    private Integer redirectAfter;

    private TransactionStatus status;

}
