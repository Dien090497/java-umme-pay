package vn.unicloud.umeepay.client.testapi.paygate.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unicloud.umeepay.core.BaseResponseData;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquiryCheckingClientResponse extends BaseResponseData {

    private String displayName;

    private String actualAccount;

}
