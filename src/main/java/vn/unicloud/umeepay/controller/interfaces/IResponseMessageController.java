package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.umeepay.config.OpenApiConfig;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.message.request.CreateResponseMessageRequest;
import vn.unicloud.umeepay.dtos.message.request.UpdateResponseMessageRequest;
import vn.unicloud.umeepay.dtos.message.response.ListResponseMessageResponse;
import vn.unicloud.umeepay.dtos.message.response.ResponseMessageResponse;

import javax.validation.Valid;

@Tag(name = "Response Message Controller", description = "Thao tác với Response message")
@RequestMapping(value = "/api/message")
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public interface IResponseMessageController {

    @Operation(
            summary = "Xem tất cả response message ",
            description = "- Xem tất cả response message",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @GetMapping("/cms/v1/getAll")
    ResponseEntity<ResponseBase<ListResponseMessageResponse>> getAllResponseMessages();

    @Operation(
            summary = "Tạo mới response message ",
            description = "- Tạo mới response message",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @PostMapping("/cms/v1/create")
    ResponseEntity<ResponseBase<ResponseMessageResponse>> createResponseMessage(@RequestBody @Valid CreateResponseMessageRequest request);

    @Operation(
            summary = "Xem thông tin response message với ID ",
            description = "- Xem thông tin response message với ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @GetMapping("/cms/v1/getDetail/{id}")
    ResponseEntity<ResponseBase<ResponseMessageResponse>> getDetailResponseMessage(@PathVariable Long id);

    @Operation(
            summary = "Xem thông tin response message với response code ",
            description = "- Xem thông tin response message với response code",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @GetMapping("/cms/v1/getByCode/{code}")
    ResponseEntity<ResponseBase<ResponseMessageResponse>> getDetailResponseMessage(@PathVariable Integer code);

    @Operation(
            summary = "Xóa response message ",
            description = "- Xóa response message",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @DeleteMapping("/cms/v1/delete/{id}")
    ResponseEntity<ResponseBase<ResponseMessageResponse>> deleteResponseMessage(@PathVariable Long id);

    @Operation(
            summary = "Chỉnh sửa response message ",
            description = "- Chỉnh sửa response message",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @PutMapping("/cms/v1/update/{id}")
    ResponseEntity<ResponseBase<ResponseMessageResponse>> updateResponseMessage(@PathVariable Long id, @RequestBody @Valid UpdateResponseMessageRequest request);

}
