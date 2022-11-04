package vn.unicloud.umeepay.dtos.user.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CheckOTPResponse extends BaseResponseData {

    private String sessionId;

    private String token;

    private int timeout;

}
