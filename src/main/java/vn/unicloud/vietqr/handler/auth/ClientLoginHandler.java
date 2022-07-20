package vn.unicloud.vietqr.handler.auth;

import org.springframework.stereotype.Component;
import vn.unicloud.vietqr.core.RequestHandler;
import vn.unicloud.vietqr.dtos.request.ClientLoginRequest;
import vn.unicloud.vietqr.dtos.response.AccessTokenResponseCustom;
import vn.unicloud.vietqr.service.AuthService;

@Component
public class ClientLoginHandler extends RequestHandler<ClientLoginRequest, AccessTokenResponseCustom> {

    private final AuthService authService;

    public ClientLoginHandler(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public AccessTokenResponseCustom handle(ClientLoginRequest request) {
        return authService.clientLogin(request);
    }
}
