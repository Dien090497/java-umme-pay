package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.umeepay.config.OpenApiConfig;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.admin.request.CreateAdminRequest;
import vn.unicloud.umeepay.dtos.admin.request.UpdateAdminRequest;
import vn.unicloud.umeepay.dtos.admin.response.AdminDetailResponse;
import vn.unicloud.umeepay.dtos.admin.response.AdminResponse;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.enums.Office;
import vn.unicloud.umeepay.enums.UserStatus;
import vn.unicloud.umeepay.utils.Constants;

import javax.validation.Valid;

@Tag(name = "Admin Controller", description = "Thao tác với quản trị viên")
@RequestMapping(value = "/api/cms/admin")
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public interface IAdminController {

    @Operation(
            summary = "Tạo mới quản trị viên",
            description = "- Tạo mới quản trị viên",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @PostMapping("/v1/create")
    ResponseEntity<ResponseBase<AdminResponse>> createAdmin(@RequestBody @Valid CreateAdminRequest request);

    @Operation(
            summary = "Chỉnh sửa quản trị viên",
            description = "- Chỉnh  quản trị viên",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @PutMapping("/v1/update/{id}")
    ResponseEntity<ResponseBase<AdminResponse>> updateAdmin(@PathVariable String id, @RequestBody @Valid UpdateAdminRequest request);

    @Operation(
            summary = "Xem chi tiết quản trị viên",
            description = "- Xem chi tiết quản trị viên",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @GetMapping("/v1/getDetail/{id}")
    ResponseEntity<ResponseBase<AdminDetailResponse>> getAdminDetail(@PathVariable String id);

    @Operation(
            summary = "Xem danh sách quản trị viên",
            description = "-Xem danh sách quản trị viên",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @GetMapping("/v1/getAll")
    ResponseEntity<ResponseBase<PageResponse<AdminResponse>>> getAll(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String staffId,
            @RequestParam(required = false) Office office,
            @RequestParam(required = false) UserStatus status,
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_NUMBER_STRING) Integer page,
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE_STRING) Integer pageSize,
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SORT_DIRECTION) Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SORT_BY) String sortBy);
}