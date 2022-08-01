package vn.unicloud.vietqr.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.vietqr.core.ResponseBase;
import vn.unicloud.vietqr.dtos.paygate.request.NotifyTransactionRequest;
import vn.unicloud.vietqr.dtos.paygate.response.DepositCheckingResponse;
import vn.unicloud.vietqr.dtos.paygate.response.InquiryCheckingResponse;
import vn.unicloud.vietqr.dtos.paygate.response.NotifyTransactionResponse;

import javax.validation.Valid;

@Tag(name = "Paygate Callback Controller", description = "Handle callback từ paygate")
@RequestMapping(value = "/api/paygate/callback")
public interface IPaygateController {

    @Operation(
        summary = "Kiểm tra tài khoản ảo",
        description = "- Kiểm tra tài khoản ảo"
    )
    @GetMapping("/v1/inquiryChecking")
    ResponseEntity<ResponseBase<InquiryCheckingResponse>> inquiryChecking(@RequestParam String virtualAccount);

    @Operation(
        summary = "Kiểm tra trước khi gọi hoạch toán",
        description = "- Kiểm tra trước khi gọi hoạch toán"
    )
    @GetMapping("/v1/depositChecking")
    ResponseEntity<ResponseBase<DepositCheckingResponse>> depositChecking(@RequestParam String virtualAccount, @RequestParam Long amount);

    @Operation(
        summary = "Cập nhật trạng thái giao dịch",
        description = "- Cập nhật trạng thái giao dịch"
    )
    @PostMapping("/v1/notifyTransaction")
    ResponseEntity<ResponseBase<NotifyTransactionResponse>> notifyTransaction(@Valid @RequestBody NotifyTransactionRequest request);

}
