package vn.unicloud.umeepay.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IAdminRoleController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.dtos.role.request.*;
import vn.unicloud.umeepay.dtos.role.response.GetAllPermissionResponse;
import vn.unicloud.umeepay.dtos.role.response.RoleDetailResponse;
import vn.unicloud.umeepay.dtos.role.response.RoleResponse;
import vn.unicloud.umeepay.enums.RoleStatus;
import vn.unicloud.umeepay.enums.RoleType;

@RestController
public class AdminRoleController extends BaseController implements IAdminRoleController {

    @Override
    public ResponseEntity<ResponseBase<PageResponse<RoleResponse>>> getAllRoles(String code,
                                                                                String name,
                                                                                RoleStatus status,
                                                                                Integer page,
                                                                                Integer pageSize,
                                                                                Sort.Direction sortDirection,
                                                                                String sortBy) {

        GetListRoleRequest request = new GetListRoleRequest()
                .setCode(code)
                .setName(name)
                .setStatus(status)
                .setScope(RoleType.ADMIN) // Get only admin roles
                .setPageable(PageRequest.of(page, pageSize, Sort.by(sortDirection, sortBy)));

        return this.execute(request, (Class) PageResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<GetAllPermissionResponse>> getAllPermissions() {
        GetAllPermissionRequest request = new GetAllPermissionRequest(RoleType.ADMIN);
        return this.execute(request, GetAllPermissionResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<RoleResponse>> createAdminRole(CreateRoleRequest request) {
        request.setScope(RoleType.ADMIN);
        return this.execute(request, RoleResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<RoleDetailResponse>> getRoleDetail(Long roleId) {
        return this.execute(new GetRoleDetailRequest(roleId), RoleDetailResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<RoleResponse>> updateRole(Long roleId, UpdateRoleRequest request) {
        request.setId(roleId);
        return this.execute(request, RoleResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<RoleResponse>> delete(Long roleId) {
        return this.execute(new DeleteRoleRequest(roleId), RoleResponse.class);
    }
}
