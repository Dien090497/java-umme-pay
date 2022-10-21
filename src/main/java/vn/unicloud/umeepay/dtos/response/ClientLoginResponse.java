package vn.unicloud.umeepay.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import vn.unicloud.umeepay.core.BaseResponseData;

@Getter
@Setter
@ToString
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
