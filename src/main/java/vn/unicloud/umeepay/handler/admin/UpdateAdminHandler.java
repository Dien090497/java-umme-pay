package vn.unicloud.umeepay.handler.admin;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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

        admin.setEmail(request.getEmail() != null ? request.getEmail() : admin.getEmail())
                .setOffice(request.getOffice() != null ? request.getOffice() : admin.getOffice())
                .setPhone(request.getPhone() != null ? request.getPhone() : admin.getPhone())
                .setFullName(request.getFullName() != null ? request.getFullName() : admin.getFullName())
                .setStaffId(request.getStaffId() != null ? request.getStaffId() : admin.getStaffId())
                .setDescription(request.getDescription() != null ? request.getDescription() : admin.getDescription());

        // update keycloak user
        String userId = keycloakService.updateUser(admin.getId(), request.getEmail(), request.getFullName());

        if (userId != null && adminService.saveAdmin(admin) != null) {
            return new AdminResponse(admin);
        }

        throw new InternalException(ResponseCode.FAILED);
    }

}
