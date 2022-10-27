package vn.unicloud.umeepay.dtos.user.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.merchant.User;
import vn.unicloud.umeepay.enums.UserStatus;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CheckPhoneResponse extends BaseResponseData {

    private String sessionId;
    private int timeout;

}
