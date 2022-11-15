package vn.unicloud.umeepay.handler.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.admin.request.UpdateAdminRequest;
import vn.unicloud.umeepay.dtos.admin.response.AdminResponse;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.entity.RoleGroup;
import vn.unicloud.umeepay.enums.OfficeType;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.RoleStatus;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.AdminService;
import vn.unicloud.umeepay.service.KeycloakService;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.service.RoleService;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

@Component
@RequiredArgsConstructor
public class UpdateAdminHandler extends RequestHandler<UpdateAdminRequest, AdminResponse> {

    private final KeycloakService keycloakService;

    private final AdminService adminService;

    private final RoleService roleService;

    private final RedisService redisService;

    @Override
    @Transactional
    public AdminResponse handle(UpdateAdminRequest request) {
        Administrator admin = adminService.getById(request.getId());
        if (admin == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }

        String email = request.getEmail() != null ? request.getEmail() : admin.getEmail();
        OfficeType office = request.getOffice() != null ? request.getOffice() : admin.getOffice();
        String phone = request.getPhone() != null ? request.getPhone() : admin.getPhone();
        String fullName = request.getFullName() != null ? request.getFullName() : admin.getFullName();
        String staffId = request.getStaffId() != null ? request.getStaffId() : admin.getStaffId();
        String description = request.getDescription() != null ? request.getDescription() : admin.getDescription();

        admin.setEmail(email)
                .setOffice(office)
                .setPhone(phone)
                .setFullName(fullName)
                .setStaffId(staffId)
                .setDescription(description);

        // Update role
        if (request.getRoleGroupId() != null) {
            RoleGroup role = roleService.getRoleById(request.getRoleGroupId());
            if (role == null) {
                throw new InternalException(ResponseCode.ROLE_ERROR_NOT_FOUND);
            }

            if (!RoleStatus.ACTIVE.equals(role.getStatus())) {
                throw new InternalException(ResponseCode.ROLE_ERROR_CLOSED);
            }

            admin.setRoleGroup(role);
            redisService.setValue(RedisKeyUtils.getUserRoleKey(admin.getId()), role.getId());
        }

        // update keycloak user
        String keycloakUserId = keycloakService.updateUser(admin.getSubjectId(), email, fullName);

        if (keycloakUserId != null && adminService.saveAdmin(admin) != null) {
            return new AdminResponse(admin);
        }

        throw new InternalException(ResponseCode.FAILED);
    }

}
