package vn.unicloud.umeepay.handler.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.role.request.DeleteRoleRequest;
import vn.unicloud.umeepay.dtos.role.response.RoleResponse;
import vn.unicloud.umeepay.entity.Role;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.RoleService;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteRoleHandler extends RequestHandler<DeleteRoleRequest, RoleResponse> {

    private final RoleService roleService;

    @Override
    @Transactional
    public RoleResponse handle(DeleteRoleRequest request) {
        Role role = roleService.getRoleById(request.getId());
        if (role == null) {
            throw new InternalException(ResponseCode.ROLE_ERROR_NOT_FOUND);
        }

        if (!roleService.getAdminsInRole(role).isEmpty()) {
            throw new InternalException(ResponseCode.ROLE_ERROR_NOT_EMPTY);
        }

        if(roleService.deleteRole(role) != null) {
            return new RoleResponse(role);
        }

        throw new InternalException(ResponseCode.FAILED);
    }
}
