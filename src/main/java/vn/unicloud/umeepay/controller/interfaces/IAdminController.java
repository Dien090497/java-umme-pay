package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.umeepay.config.OpenApiConfig;
import vn.unicloud.umeepay.constant.BaseConstant;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.admin.request.CreateAdminRequest;
import vn.unicloud.umeepay.dtos.admin.request.UpdateAdminRequest;
import vn.unicloud.umeepay.dtos.admin.response.AdminDetailResponse;
import vn.unicloud.umeepay.dtos.admin.response.AdminResponse;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.enums.OfficeType;
import vn.unicloud.umeepay.enums.UserStatus;

import javax.validation.Valid;

@Tag(name = "Admin Controller", description = "Thao tác với quản trị viên")
@RequestMapping(value = "/api/admin/cms")
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public interface IAdminController {

    @Operation(summary = "Tạo mới quản trị viên")
    @PostMapping("/v1/create")
    ResponseEntity<ResponseBase<AdminResponse>> createAdmin(@RequestBody @Valid CreateAdminRequest request);

    @Operation(summary = "Chỉnh sửa quản trị viên")
    @PutMapping("/v1/update/{id}")
    ResponseEntity<ResponseBase<AdminResponse>> updateAdmin(@PathVariable String id, @RequestBody @Valid UpdateAdminRequest request);

    @Operation(summary = "Xem chi tiết quản trị viên")
    @GetMapping("/v1/getDetail/{id}")
    ResponseEntity<ResponseBase<AdminDetailResponse>> getAdminDetail(@PathVariable String id);

    @Operation(summary = "Xem danh sách quản trị viên")
    @GetMapping("/v1/getAll")
    ResponseEntity<ResponseBase<PageResponse<AdminResponse>>> getAll(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String staffId,
            @RequestParam(required = false) OfficeType office,
            @RequestParam(required = false) UserStatus status,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_NUMBER_STRING) Integer page,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SIZE_STRING) Integer pageSize,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SORT_DIRECTION) Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SORT_BY) String sortBy);

    @Operation(
        summary = "Xóa quản trị viên",
        description = "- Xóa quản trị viên nếu chưa từng đăng nhập vào hệ thống"
    )
    @DeleteMapping("/v1/delete/{id}")
    ResponseEntity<ResponseBase<AdminResponse>> deleteAdmin(@PathVariable String id);

    @Operation(summary = "Vô hiện hóa quản trị viên")
    @PutMapping("/v1/block/{id}")
    ResponseEntity<ResponseBase<AdminResponse>> blockAdmin(@PathVariable String id);

    @Operation(summary = "Kích hoạt (mở khóa) quản trị viên")
    @PutMapping("/v1/unblock/{id}")
    ResponseEntity<ResponseBase<AdminResponse>> unblockAdmin(@PathVariable String id);

}