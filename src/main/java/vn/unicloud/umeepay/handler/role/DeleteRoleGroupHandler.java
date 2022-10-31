package vn.unicloud.umeepay.handler.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.role.request.DeleteRoleGroupRequest;
import vn.unicloud.umeepay.dtos.role.response.RoleGroupResponse;
import vn.unicloud.umeepay.entity.RoleGroup;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.RoleService;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteRoleGroupHandler extends RequestHandler<DeleteRoleGroupRequest, RoleGroupResponse> {

    private final RoleService roleService;

    @Override
    @Transactional
    public RoleGroupResponse handle(DeleteRoleGroupRequest request) {
        RoleGroup role = roleService.getRoleById(request.getId());
        if (role == null) {
            throw new InternalException(ResponseCode.ROLE_ERROR_NOT_FOUND);
        }

        if (!role.getUsers().isEmpty() || !role.getAdmins().isEmpty()) {
            throw new InternalException(ResponseCode.ROLE_ERROR_NOT_EMPTY);
        }

        if(roleService.deleteRole(role) != null) {
            return new RoleGroupResponse(role);
        }

        throw new InternalException(ResponseCode.FAILED);
    }
}
