package vn.unicloud.vietqr.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.unicloud.vietqr.dtos.request.ClientLoginRequest;
import vn.unicloud.vietqr.dtos.response.AccessTokenResponseCustom;
import vn.unicloud.vietqr.enums.ResponseCode;
import vn.unicloud.vietqr.exception.InternalException;

@Service
@Log4j2
public class AuthService {

    @Autowired
    private KeycloakService keycloakService;

    public AccessTokenResponseCustom clientLogin(ClientLoginRequest request) {
        AccessTokenResponseCustom responseCustom = keycloakService.clientLogin(request.getClientId(), request.getSecretKey());
        if (responseCustom == null) {
            throw new InternalException(ResponseCode.CLIENT_LOGIN_FAILED);
        }
        return responseCustom;
    }
}
