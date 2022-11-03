package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.umeepay.config.OpenApiConfig;
import vn.unicloud.umeepay.constant.BaseConstant;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.dtos.role.request.CreateRoleGroupRequest;
import vn.unicloud.umeepay.dtos.role.request.UpdateRoleGroupRequest;
import vn.unicloud.umeepay.dtos.role.response.GetAllPermissionResponse;
import vn.unicloud.umeepay.dtos.role.response.RoleGroupDetailResponse;
import vn.unicloud.umeepay.dtos.role.response.RoleGroupResponse;
import vn.unicloud.umeepay.enums.RoleStatus;

import javax.validation.Valid;

@Tag(name = "Role Group Controller", description = "Thao tác với nhóm (quyền) của quản trị viên")
@RequestMapping(value = "/api/roleGroup/cms")
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public interface IAdminRoleController {

    @Operation(summary = "Xem danh sách nhóm quyền quản trị viên")
    @GetMapping("/v1/getAll")
    ResponseEntity<ResponseBase<PageResponse<RoleGroupResponse>>> getAllRoleGroups(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) RoleStatus status,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_NUMBER_STRING) Integer page,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SIZE_STRING) Integer pageSize,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SORT_DIRECTION) Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SORT_BY) String sortBy
    );

    @Operation(summary = "Xem danh sách nhóm permissions quản trị viên")
    @GetMapping("/v1/permission/getAll")
    ResponseEntity<ResponseBase<GetAllPermissionResponse>> getAllPermissions();

    @Operation(summary = "Tạo mới nhóm quyền quản trị viên")
    @PostMapping("/v1/create")
    ResponseEntity<ResponseBase<RoleGroupResponse>> createAdminRoleGroup(@RequestBody @Valid CreateRoleGroupRequest request);

    @Operation(summary = "Xem chi tiết nhóm quyền quản trị viên")
    @GetMapping("/v1/getDetail/{groupId}")
    ResponseEntity<ResponseBase<RoleGroupDetailResponse>> getRoleGroupDetail(@PathVariable Long groupId);

    @Operation(summary = "Chỉnh sửa nhóm quyền quản trị viên")
    @PutMapping("/v1/update/{groupId}")
    ResponseEntity<ResponseBase<RoleGroupResponse>> updateRoleGroup(@PathVariable Long groupId, @RequestBody @Valid UpdateRoleGroupRequest request);

    @Operation(summary = "Xóa nhóm quyền quản trị viên")
    @DeleteMapping("/v1/delete/{groupId}")
    ResponseEntity<ResponseBase<RoleGroupResponse>> deleteRoleGroup(@PathVariable Long groupId);


}
