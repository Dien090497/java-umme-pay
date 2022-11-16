package vn.unicloud.umeepay.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.ICmsRoleController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.dtos.role.request.*;
import vn.unicloud.umeepay.dtos.role.response.GetAllPermissionResponse;
import vn.unicloud.umeepay.dtos.role.response.RoleGroupDetailResponse;
import vn.unicloud.umeepay.dtos.role.response.RoleGroupResponse;
import vn.unicloud.umeepay.enums.RoleStatus;
import vn.unicloud.umeepay.enums.RoleType;

@RestController
public class CmsRoleController extends BaseController implements ICmsRoleController {

    @Override
    public ResponseEntity<ResponseBase<PageResponse<RoleGroupResponse>>> getAllAdminRoleGroups(String code,
                                                                                               String name,
                                                                                               RoleStatus status,
                                                                                               Integer page,
                                                                                               Integer pageSize,
                                                                                               Sort.Direction sortDirection,
                                                                                               String sortBy) {

        GetListRoleGroupRequest request = new GetListRoleGroupRequest()
                .setCode(code)
                .setName(name)
                .setStatus(status)
                .setScope(RoleType.ADMIN) // Get only admin roles
                .setPageable(PageRequest.of(page, pageSize, Sort.by(sortDirection, sortBy)));

        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetAllPermissionResponse>> getAllAdminPermissions() {
        GetAllPermissionRequest request = new GetAllPermissionRequest(RoleType.ADMIN);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<RoleGroupResponse>> createAdminRoleGroup(CreateRoleGroupRequest request) {
        request.setScope(RoleType.ADMIN);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<PageResponse<RoleGroupResponse>>> getAllMerchantRoleGroups(String code,
                                                                                                  String name,
                                                                                                  RoleStatus status,
                                                                                                  Integer page,
                                                                                                  Integer pageSize,
                                                                                                  Sort.Direction sortDirection,
                                                                                                  String sortBy) {

        GetListRoleGroupRequest request = new GetListRoleGroupRequest()
                .setCode(code)
                .setName(name)
                .setStatus(status)
                .setScope(RoleType.MERCHANT) // Get only merchant roles
                .setPageable(PageRequest.of(page, pageSize, Sort.by(sortDirection, sortBy)));

        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetAllPermissionResponse>> getAllMerchantPermissions() {
        GetAllPermissionRequest request = new GetAllPermissionRequest(RoleType.MERCHANT);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<RoleGroupResponse>> createMerchantRoleGroup(CreateRoleGroupRequest request) {
        request.setScope(RoleType.MERCHANT);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<RoleGroupDetailResponse>> getRoleGroupDetail(Long groupId) {
        return this.execute(new GetRoleGroupDetailRequest(groupId));
    }

    @Override
    public ResponseEntity<ResponseBase<RoleGroupResponse>> updateRoleGroup(Long groupId, UpdateRoleGroupRequest request) {
        request.setId(groupId);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<RoleGroupResponse>> deleteRoleGroup(Long groupId) {
        return this.execute(new DeleteRoleGroupRequest(groupId));
    }
}
