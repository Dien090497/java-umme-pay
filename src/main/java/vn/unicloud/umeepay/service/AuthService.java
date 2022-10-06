package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.dtos.request.ClientLoginRequest;
import vn.unicloud.umeepay.dtos.response.AccessTokenResponseCustom;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthService {

    private final KeycloakService keycloakService;

    public AccessTokenResponseCustom clientLogin(ClientLoginRequest request) {
        AccessTokenResponseCustom responseCustom = keycloakService.clientLogin(request.getClientId(), request.getSecretKey());
        if (responseCustom == null) {
            throw new InternalException(ResponseCode.CLIENT_LOGIN_FAILED);
        }
        return responseCustom;
    }
}
