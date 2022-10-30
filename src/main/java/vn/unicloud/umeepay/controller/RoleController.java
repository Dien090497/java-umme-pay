package vn.unicloud.umeepay.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IRoleController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.admin.request.DeleteAdminRequest;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.dtos.role.request.*;
import vn.unicloud.umeepay.dtos.role.response.RoleDetailResponse;
import vn.unicloud.umeepay.dtos.role.response.RoleResponse;
import vn.unicloud.umeepay.enums.RoleStatus;
import vn.unicloud.umeepay.enums.RoleType;

@RestController
public class RoleController extends BaseController implements IRoleController {

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
    public ResponseEntity<ResponseBase<RoleResponse>> createAdminRole(CreateAdminRoleRequest request) {
        return this.execute(request, RoleResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<RoleDetailResponse>> getRoleDetail(Long roleId) {
        return this.execute(new GetRoleDetailRequest(roleId), RoleDetailResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<RoleResponse>> updateRole(Long roleId, UpdateAdminRoleRequest request) {
        request.setId(roleId);
        return this.execute(request, RoleResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<RoleResponse>> delete(Long roleId) {
        return this.execute(new DeleteRoleRequest(roleId), RoleResponse.class);
    }
}
