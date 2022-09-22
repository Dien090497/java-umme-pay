package vn.unicloud.umeepay.handler.auth;

import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.request.ClientLoginRequest;
import vn.unicloud.umeepay.dtos.response.AccessTokenResponseCustom;
import vn.unicloud.umeepay.service.AuthService;

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
