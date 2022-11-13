package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.unicloud.umeepay.config.OpenApiConfig;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.audit.response.AuditVersionResponse;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.enums.SystemModule;

@Tag(name = "Audit Controller", description = "Thao tác với version của các module")
@RequestMapping(value = "/api/audit")
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public interface IAuditController {

    @Operation(summary = "Lấy version của 1 module")
    @GetMapping("/v1/currentVersion")
    ResponseEntity<ResponseBase<AuditVersionResponse>> getCurrentVersion(@RequestParam SystemModule module);

}
