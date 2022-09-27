package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.umeepay.core.BaseResponseData;

@Data
@AllArgsConstructor
public class GetMerchantCredentialResponse extends BaseResponseData {

    private boolean success;

    private String umeePublicKey;

    private String clientPublicKey;

    private String secretKey;

    private String clientId;

}
