package vn.unicloud.umeepay.handler.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.auth.request.AdminLogoutRequest;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.service.KeycloakService;

@Component
@RequiredArgsConstructor
public class AdminLogoutHandler extends RequestHandler<AdminLogoutRequest, StatusResponse> {

    private final KeycloakService keycloakService;

    @Override
    public StatusResponse handle(AdminLogoutRequest request) {
        boolean result = keycloakService.invalidateToken(request.getRefreshToken());
        return new StatusResponse(result);
    }
}
