package vn.unicloud.umeepay.handler.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.admin.request.DeleteAdminRequest;
import vn.unicloud.umeepay.dtos.admin.response.AdminResponse;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.AdminService;
import vn.unicloud.umeepay.service.KeycloakService;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteAdminHandler extends RequestHandler<DeleteAdminRequest, AdminResponse> {

    private final AdminService adminService;

    private final KeycloakService keycloakService;

    @Override
    @Transactional
    public AdminResponse handle(DeleteAdminRequest request) {
        Administrator deletedAdmin = adminService.getById(request.getId());
        if (deletedAdmin == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }

        if (deletedAdmin.getLoggedIn()) {
            throw new InternalException(ResponseCode.LOGGED_IN_ACCOUNT);
        }

        if (adminService.deleteAdmin(deletedAdmin) != null) {
            keycloakService.deleteUser(deletedAdmin.getId());
            return new AdminResponse(deletedAdmin);
        }

        throw new InternalException(ResponseCode.FAILED);
    }

}
