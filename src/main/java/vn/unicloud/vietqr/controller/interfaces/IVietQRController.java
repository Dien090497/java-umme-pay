package vn.unicloud.vietqr.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.vietqr.core.ResponseBase;
import vn.unicloud.vietqr.dtos.vietqr.request.ConfirmTransactionRequest;
import vn.unicloud.vietqr.dtos.vietqr.request.CreateTransactionRequest;
import vn.unicloud.vietqr.dtos.vietqr.request.RollbackTransactionRequest;
import vn.unicloud.vietqr.dtos.vietqr.response.*;

import javax.validation.Valid;

@Tag(name = "VietQR Controller", description = "Thao tác với VietQR")
@RequestMapping(value = "/api/viet-qr")
public interface IVietQRController {

    @Operation(
        summary = "1. Tạo một giao dịch rút tiền bằng VietQR mới",
        description = "- Tạo một giao dịch rút tiền bằng VietQR mới, trả về QR code"
    )
    @PostMapping("/v1/create")
    ResponseEntity<ResponseBase<CreateTransactionResponse>> createTransaction(@Valid @RequestBody CreateTransactionRequest request);

    @Operation(
        summary = "2. Long pooling để kiểm tra giao dịch",
        description = "- Long pooling để kiểm tra giao dịch, có thể trả về timeout hoặc thành công"
    )
    @GetMapping(value = "/v1/check")
    ResponseEntity<ResponseBase<CheckTransactionResponse>> checkTransaction(@RequestParam String transactionId);

    @Operation(
        summary = "Huỷ giao dịch",
        description = "- Huỷ giao dịch từ phía máy"
    )
    @PutMapping(value = "/v1/cancel")
    ResponseEntity<ResponseBase<CancelTransactionResponse>> cancelTransaction(@RequestParam String transactionId);

    @Operation(
        summary = "3. Confirm chi tiền thành công",
        description = "- Confirm chi tiền thành công"
    )
    @PostMapping("/v1/confirm")
    ResponseEntity<ResponseBase<ConfirmTransactionResponse>> confirmTransaction(@Valid @RequestBody ConfirmTransactionRequest request);

    @Operation(
        summary = "3. Rollback giao dịch trong trường hợp máy chi tiền bị lỗi",
        description = "- Rollback giao dịch trong trường hợp máy chi tiền bị lỗi"
    )
    @RequestMapping(value = "/v1/rollback", method = RequestMethod.POST)
    ResponseEntity<ResponseBase<RollbackTransactionResponse>> rollback(@Valid @RequestBody RollbackTransactionRequest request);

}
