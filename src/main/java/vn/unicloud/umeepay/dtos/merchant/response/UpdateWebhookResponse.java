package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UpdateWebhookResponse extends BaseResponseData {

    private boolean success;

}
