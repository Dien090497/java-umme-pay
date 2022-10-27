package vn.unicloud.umeepay.handler.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.admin.request.UpdateAdminRequest;
import vn.unicloud.umeepay.dtos.admin.response.AdminResponse;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.AdminService;
import vn.unicloud.umeepay.service.KeycloakService;

@Component
@RequiredArgsConstructor
public class UpdateAdminHandler extends RequestHandler<UpdateAdminRequest, AdminResponse> {

    private final KeycloakService keycloakService;

    private final AdminService adminService;

    @Override
    @Transactional
    public AdminResponse handle(UpdateAdminRequest request) {
        Administrator admin = adminService.getById(request.getId());
        if (admin == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }

        if (!admin.getEmail().equals(request.getEmail()) &&
                adminService.getByEmail(request.getEmail()) != null) {
            throw new InternalException(ResponseCode.EXISTED_EMAIL);
        }

        admin.setEmail(request.getEmail())
                .setOffice(request.getOffice())
                .setPhone(request.getPhone())
                .setFullName(request.getFullName())
                .setStaffId(request.getStaffId())
                .setDescription(request.getDescription());

        // update keycloak user
        String userId = keycloakService.updateUser(admin.getId(), request.getEmail(), request.getFullName());

        if (userId != null && adminService.saveAdmin(admin) != null) {
            return new AdminResponse(admin);
        }

        throw new InternalException(ResponseCode.FAILED);
    }

}
