package vn.unicloud.vietqr.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.vietqr.core.BaseResponseData;

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
