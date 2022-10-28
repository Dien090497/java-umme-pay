package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.unicloud.umeepay.config.OpenApiConfig;
import vn.unicloud.umeepay.core.ResponseBase;

@Tag(name = "Role Controller", description = "Thao tác với nhóm (quyền) của quản trị viên")
@RequestMapping(value = "/api/role/cms")
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public interface IRoleController {
    @Operation(
            summary = "Tạo mới nhóm quyền",
            description = "- Tạo mới nhóm quyền",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @PostMapping("/v1/create")
    ResponseEntity<ResponseBase<>> createRole();
}
