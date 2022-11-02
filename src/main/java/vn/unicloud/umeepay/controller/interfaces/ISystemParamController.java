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
import vn.unicloud.umeepay.dtos.paramter.request.CreateSystemParamRequest;
import vn.unicloud.umeepay.dtos.paramter.request.UpdateSystemParamRequest;
import vn.unicloud.umeepay.dtos.paramter.response.AllSystemParamResponse;
import vn.unicloud.umeepay.dtos.paramter.response.SystemParamResponse;
import vn.unicloud.umeepay.enums.SystemParameterGroup;
import vn.unicloud.umeepay.enums.SystemParameterType;

import javax.validation.Valid;

@Tag(name = "System parameter Controller", description = "Thao tác với tham số hệ thống")
@RequestMapping(value = "/api/parameter")
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public interface ISystemParamController {

    @Operation(
            summary = "Xem tất cả tham số hệ thống",
            description = "- Xem tất cả các tham số hệ thống",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @GetMapping("/cms/v1/getAll")
    ResponseEntity<ResponseBase<AllSystemParamResponse>> getAllParameters(
            @RequestParam(required = false) SystemParameterGroup group,
            @RequestParam(required = false) SystemParameterType dataType,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SORT_DIRECTION) Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SORT_BY) String sortBy);

    @Operation(
            summary = "Chỉnh sửa tham số hệ thống",
            description = "- Chỉnh sửa tham số hệ thống",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @PutMapping("/cms/v1/update/{id}")
    ResponseEntity<ResponseBase<SystemParamResponse>> updateParameter(@PathVariable Long id, @RequestBody @Valid UpdateSystemParamRequest request);

    @Operation(
            summary = "Tạo mới tham số hệ thống",
            description = "- Tạo mới tham số hệ thống",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @PostMapping("/cms/v1/create")
    ResponseEntity<ResponseBase<SystemParamResponse>> createParameter(@RequestBody @Valid CreateSystemParamRequest request);

    @Operation(
            summary = "Xóa tham số hệ thống",
            description = "- Xóa tham số hệ thống",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @DeleteMapping("/cms/v1/delete/{id}")
    ResponseEntity<ResponseBase<SystemParamResponse>> deleteParameter(@PathVariable Long id);

}
