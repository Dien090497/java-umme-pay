package vn.unicloud.umeepay.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.umeepay.core.BaseResponseData;

@Data
@AllArgsConstructor
public class ClientLoginResponse extends BaseResponseData {

    private String token;

    private long expiresIn;

    private long refreshExpiresIn;

    private String refreshToken;

    private String tokenType;

    private String idToken;

    private int notBeforePolicy;

    private String sessionState;

}
