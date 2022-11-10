package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.unicloud.umeepay.config.OpenApiConfig;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.ekyc.request.DetectCardRequest;
import vn.unicloud.umeepay.dtos.ekyc.response.EkycResponse;

import javax.validation.Valid;

@Tag(name = "Ekyc Controller", description = "Thao tác OCR với Ekyc")
@RequestMapping(value = "/api/ekyc")
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public interface IEkycController {

    @Operation(summary = "OCR CMND, CCCD, Passport,.. với ekyc")
    @PostMapping(value = "/v1/detectCard", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ResponseBase<EkycResponse>> detectCard(@ModelAttribute @Valid DetectCardRequest request);

}
