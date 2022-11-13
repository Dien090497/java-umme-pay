package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.BankAccountDto;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubmitLinkAccountResponse extends BaseResponseData {

    private String sessionId;

}
