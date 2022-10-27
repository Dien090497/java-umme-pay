package vn.unicloud.umeepay.handler.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.admin.request.CreateAdminRequest;
import vn.unicloud.umeepay.dtos.admin.response.AdminResponse;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.RoleType;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.AdminService;
import vn.unicloud.umeepay.service.KeycloakService;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateAdminHandler extends RequestHandler<CreateAdminRequest, AdminResponse> {

    private final AdminService adminService;

    private final KeycloakService keycloakService;

    @Override
    @Transactional
    public AdminResponse handle(CreateAdminRequest request) {
        String username = request.getUsername();
        String email = request.getEmail();

        if (adminService.getByUsername(username) != null) {
            throw new InternalException(ResponseCode.EXISTED_USERNAME);
        }

        if (adminService.getByEmail(email) != null) {
            throw new InternalException(ResponseCode.EXISTED_EMAIL);
        }

        Administrator admin = new Administrator()
                .setUsername(username)
                .setEmail(email)
                .setFullName(request.getFullName())
                .setStaffId(request.getStaffId())
                .setPhone(request.getPhone())
                .setOffice(request.getOffice())
                .setDescription(request.getDescription());

        // Create randomPassword
//        String password = CommonUtils.getSecureRandomKey(8);
        String password = "12345678";

        //create key cloak user
        List<String> adminRoles = Arrays.asList(RoleType.ADMIN.toString());
        String adminId = keycloakService.createUser(admin.getUsername(), password, admin.getEmail(), admin.getFullName(), adminRoles);
        if (adminId == null) {
            throw new InternalException(ResponseCode.CREATE_USER_FAILED);
        }

        admin.setId(adminId);

        Administrator savedAdmin = adminService.saveAdmin(admin);
        if (savedAdmin == null) {
            keycloakService.deleteUser(adminId);
            throw new InternalException(ResponseCode.CREATE_USER_FAILED);
        }

        return new AdminResponse(admin);
    }
}
