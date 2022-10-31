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
import vn.unicloud.umeepay.dtos.role.request.CreateRoleRequest;
import vn.unicloud.umeepay.dtos.role.request.UpdateRoleRequest;
import vn.unicloud.umeepay.dtos.role.response.GetAllPermissionResponse;
import vn.unicloud.umeepay.dtos.role.response.RoleDetailResponse;
import vn.unicloud.umeepay.dtos.role.response.RoleResponse;
import vn.unicloud.umeepay.enums.RoleStatus;

import javax.validation.Valid;

@Tag(name = "Role Controller", description = "Thao tác với nhóm (quyền) của quản trị viên")
@RequestMapping(value = "/api/role/cms")
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public interface IAdminRoleController {

    @Operation(
            summary = "Xem danh sách nhóm quyền quản trị viên",
            description = "- Xem danh sách nhóm quyền quản trị viên",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @GetMapping("/v1/getAll")
    ResponseEntity<ResponseBase<PageResponse<RoleResponse>>> getAllRoles(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) RoleStatus status,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_NUMBER_STRING) Integer page,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SIZE_STRING) Integer pageSize,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SORT_DIRECTION) Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SORT_BY) String sortBy
    );

    @Operation(
            summary = "Xem danh sách nhóm actions quản trị viên",
            description = "- Xem danh sách nhóm nhóm actions quản trị viên",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @GetMapping("/v1/getAllPermissions")
    ResponseEntity<ResponseBase<GetAllPermissionResponse>> getAllPermissions();

    @Operation(
            summary = "Tạo mới nhóm quyền quản trị viên",
            description = "- Tạo mới nhóm quyền quản trị viên",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @PostMapping("/v1/create")
    ResponseEntity<ResponseBase<RoleResponse>> createAdminRole(@RequestBody @Valid CreateRoleRequest request);

    @Operation(
            summary = "Xem chi tiết nhóm quyền quản trị viên",
            description = "- Xem chi tiết nhóm quyền quản trị viên",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @GetMapping("/v1/getDetail/{roleId}")
    ResponseEntity<ResponseBase<RoleDetailResponse>> getRoleDetail(@PathVariable Long roleId);

    @Operation(
            summary = "Chỉnh sửa nhóm quyền quản trị viên",
            description = "- Chỉnh sửa nhóm quyền quản trị viên",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @PutMapping("/v1/update/{roleId}")
    ResponseEntity<ResponseBase<RoleResponse>> updateRole(@PathVariable Long roleId, @RequestBody @Valid UpdateRoleRequest request);

    @Operation(
            summary = "Xóa nhóm quyền quản trị viên",
            description = "- Xóa nhóm quyền quản trị viên",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @DeleteMapping("/v1/delete/{roleId}")
    ResponseEntity<ResponseBase<RoleResponse>> delete(@PathVariable Long roleId);


}
