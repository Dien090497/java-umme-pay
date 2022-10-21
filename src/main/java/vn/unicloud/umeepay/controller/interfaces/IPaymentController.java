package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.payment.request.CancelTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.request.CreateTransactionRequest;
import vn.unicloud.umeepay.dtos.request.EncryptedBodyRequest;
import vn.unicloud.umeepay.dtos.payment.request.QueryTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.response.CancelTransactionResponse;
import vn.unicloud.umeepay.dtos.payment.response.CreateTransactionResponse;
import vn.unicloud.umeepay.dtos.response.EncryptBodyResponse;
import vn.unicloud.umeepay.dtos.payment.response.QueryTransactionResponse;

import javax.validation.Valid;

@Tag(name = "Payment Controller", description = "Thao tác với Payment")
@RequestMapping(value = "/api/payment")
public interface IPaymentController {

    /**
     *
     * @param clientId
     * @param signature
     * @param timestamp
     * @param request
     * @return
     */
    @Operation(
            summary = "1. Tạo một giao dịch mới",
            description = "- Tạo một giao dịch mới, trả về QR code"
    )
    @PostMapping("/v1/create")
    ResponseEntity<ResponseBase<EncryptBodyResponse>> createTransaction(
            @RequestHeader("x-api-client") String clientId,
            @RequestHeader("x-api-validate") String signature,
            @RequestHeader("x-api-time") Long timestamp,
            @Valid @RequestBody EncryptedBodyRequest request
    );

    @Operation(
            summary = "2. Kiểm tra trạng thái giao dịch",
            description = "- Kiểm tra trạng thái giao dịch, có thể trả về timeout hoặc thành công"
    )
    @PostMapping(value = "/v1/check")
    ResponseEntity<ResponseBase<EncryptBodyResponse>> checkTransaction(
            @RequestHeader("x-api-client") String clientId,
            @RequestHeader("x-api-validate") String signature,
            @RequestHeader("x-api-time") Long timestamp,
            @Valid @RequestBody EncryptedBodyRequest request
    );

    @Operation(
            summary = "Huỷ giao dịch",
            description = "- Huỷ giao dịch từ phía merchant"
    )
    @PostMapping(value = "/v1/cancel")
    ResponseEntity<ResponseBase<EncryptBodyResponse>> cancelTransaction(
            @RequestHeader("x-api-client") String clientId,
            @RequestHeader("x-api-validate") String signature,
            @RequestHeader("x-api-time") Long timestamp,
            @Valid @RequestBody EncryptedBodyRequest request
    );


    /** Simple
     * @param keyId
     * @param secretKey
     * @param request
     * @return
     */

    @Operation(
            summary = "1. Tạo một giao dịch mới, bypass mã hoá",
            description = "- Tạo một giao dịch mới, trả về QR code"
    )
    @PostMapping("/v1/simple/create")
    ResponseEntity<ResponseBase<CreateTransactionResponse>> createTransactionSimple(
            @RequestHeader("x-api-client") String keyId,
            @RequestHeader("x-api-key") String secretKey,
            @Valid @RequestBody CreateTransactionRequest request
    );

    @Operation(
            summary = "2. Kiểm tra trạng thái giao dịch, bypass mã hoá",
            description = "- Kiểm tra trạng thái giao dịch, có thể trả về timeout hoặc thành công"
    )
    @PostMapping(value = "/v1/simple/query")
    ResponseEntity<ResponseBase<QueryTransactionResponse>> checkTransactionSimple(
            @RequestHeader("x-api-client") String keyId,
            @RequestHeader("x-api-key") String secretKey,
            @Valid @RequestBody QueryTransactionRequest request
    );

    @Operation(
            summary = "Huỷ giao dịch, bypass mã hoá",
            description = "- Huỷ giao dịch từ phía merchant"
    )
    @PostMapping(value = "/v1/simple/cancel")
    ResponseEntity<ResponseBase<CancelTransactionResponse>> cancelTransactionSimple(
            @RequestHeader("x-api-client") String keyId,
            @RequestHeader("x-api-key") String secretKey,
            @Valid @RequestBody CancelTransactionRequest request
    );

}
