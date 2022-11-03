package vn.unicloud.umeepay.handler.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.auth.request.AdminChangePasswordRequest;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.response.AccessTokenResponseCustom;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.ContextService;
import vn.unicloud.umeepay.service.KeycloakService;

@Component
@RequiredArgsConstructor
public class AdminChangePasswordHandler extends RequestHandler<AdminChangePasswordRequest, StatusResponse> {

    private final KeycloakService keycloakService;

    private final ContextService contextService;

    @Override
    public StatusResponse handle(AdminChangePasswordRequest request) {
        try {
            //Check current
            AccessTokenResponseCustom token = keycloakService.getUserJWT(contextService.getLoggedInUsername().orElse(null), request.getCurrentPassword());
            if (token == null) {
                throw new InternalException(ResponseCode.AUTH_ERROR_INCORRECT_PASSWORD);
            }

            // Change password
            String userId = contextService.getLoggedInUserId().orElse(null);
            if (userId != null
                    && keycloakService.setUserPassword(userId, request.getNewPassword())) {
                return new StatusResponse(true);
            }

        } catch (Exception ex) {
            throw new InternalException(ResponseCode.AUTH_ERROR_INCORRECT_PASSWORD);
        }

        throw new InternalException(ResponseCode.FAILED);
    }
}
