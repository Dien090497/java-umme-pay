package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
