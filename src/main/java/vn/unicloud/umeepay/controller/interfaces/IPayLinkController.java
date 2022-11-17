package vn.unicloud.umeepay.controller.interfaces;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.umeepay.config.OpenApiConfig;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.paylink.request.CreatePayLinkRequest;
import vn.unicloud.umeepay.dtos.paylink.request.UpdateCustomerInfoRequest;
import vn.unicloud.umeepay.dtos.paylink.response.*;

import javax.validation.Valid;

@RequestMapping("/api/paylink")
@Tag(name = "Paylink controller", description = "thao tác với paylink")
public interface IPayLinkController {

    @Operation(summary = "Tạo mới paylink")
    @PostMapping("/v1/create")
    @SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
    ResponseEntity<ResponseBase<PayLinkResponse>> create(@Valid @RequestBody CreatePayLinkRequest request);

    @Operation(summary = "Cập nhật thông tin khách hàng thanh toán")
    @PostMapping("/public/v1/updateCustomerInfo")
    ResponseEntity<ResponseBase<StatusResponse>> updateCustomerInfo(@Valid @RequestBody UpdateCustomerInfoRequest request);

    @Operation(summary = "Lấy thông tin paylink")
    @GetMapping("/public/v1/{payLinkCode}")
    ResponseEntity<ResponseBase<GetPayLinkResponse>> getDetail(@PathVariable String payLinkCode);

    @Operation(summary = "Thông tin thanh toán theo phương thức thanh toán VietQR")
    @GetMapping("/public/v1/vietqr/{payLinkCode}")
    ResponseEntity<ResponseBase<GetPayLinkVietQRResponse>> payVietQR(@PathVariable String payLinkCode);

    @Operation(summary = "Thông tin thanh toán theo phương thức thanh toán chuyển khoản")
    @GetMapping("/public/v1/bankTransfer/{payLinkCode}")
    ResponseEntity<ResponseBase<GetPayLinkBankTransferResponse>> payBankTransfer(@PathVariable String payLinkCode);

    @Operation(summary = "Kiểm tra trạng thái paylink")
    @GetMapping("/public/v1/query/{payLinkCode}")
    ResponseEntity<ResponseBase<PayLinkStatusResponse>> query(@PathVariable String payLinkCode);

}
