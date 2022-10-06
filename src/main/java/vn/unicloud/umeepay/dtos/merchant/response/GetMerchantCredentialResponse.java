package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GetMerchantCredentialResponse extends BaseResponseData {

    private boolean success;

    private String umeePublicKey;

    private String clientPublicKey;

    private String secretKey;

    private String clientId;

}
