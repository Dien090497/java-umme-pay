package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.umeepay.client.testapi.paygate.request.DepositCheckingClientRequest;
import vn.unicloud.umeepay.client.testapi.paygate.request.InquiryCheckingClientRequest;
import vn.unicloud.umeepay.client.testapi.paygate.request.NotifyTransactionClientRequest;
import vn.unicloud.umeepay.client.testapi.paygate.response.DepositCheckingClientResponse;
import vn.unicloud.umeepay.client.testapi.paygate.response.InquiryCheckingClientResponse;
import vn.unicloud.umeepay.client.testapi.paygate.response.NotifyTransactionClientResponse;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.paygate.request.NotifyTransactionRequest;
import vn.unicloud.umeepay.dtos.paygate.response.DepositCheckingResponse;
import vn.unicloud.umeepay.dtos.paygate.response.InquiryCheckingResponse;
import vn.unicloud.umeepay.dtos.paygate.response.NotifyTransactionResponse;
import vn.unicloud.umeepay.dtos.request.EncryptedBodyRequest;

import javax.validation.Valid;

@Tag(name = "Paygate Callback Controller", description = "Handle callback từ paygate")
@RequestMapping(value = "/api/paygate/callback")
public interface IPaygateController {


    /** simple
     *
     * @param virtualAccount
     * @return
     */

    @Operation(
        summary = "Kiểm tra tài khoản ảo",
        description = "- Kiểm tra tài khoản ảo"
    )
    @GetMapping("/v1/inquiryChecking")
    ResponseEntity<ResponseBase<InquiryCheckingResponse>> inquiryCheckingSimple(@RequestParam String virtualAccount);

    @Operation(
        summary = "Kiểm tra trước khi gọi hoạch toán",
        description = "- Kiểm tra trước khi gọi hoạch toán"
    )
    @GetMapping("/v1/depositChecking")
    ResponseEntity<ResponseBase<DepositCheckingResponse>> depositCheckingSimple(@RequestParam String virtualAccount, @RequestParam Long amount);

    @Operation(
        summary = "Cập nhật trạng thái giao dịch",
        description = "- Cập nhật trạng thái giao dịch"
    )
    @PostMapping("/v1/notifyTransaction")
    ResponseEntity<ResponseBase<NotifyTransactionResponse>> notifyTransactionSimple(@Valid @RequestBody NotifyTransactionRequest request);


    /** Encrypted Request
     *
     * @param clientId
     * @param signature
     * @param timestamp
     * @param request
     * @return
     */
    @Operation(
        summary = "Kiểm tra tài khoản ảo",
        description = "- Kiểm tra tài khoản ảo"
    )
    @PostMapping("/v2/inquiryChecking")
    ResponseEntity<ResponseBase<String>> inquiryChecking(
        @RequestHeader("x-api-client") String clientId,
        @RequestHeader("x-api-validate") String signature,
        @RequestHeader("x-api-time") Long timestamp,
        @Valid @RequestBody EncryptedBodyRequest request);

    @Operation(
        summary = "Kiểm tra trước khi gọi hoạch toán",
        description = "- Kiểm tra trước khi gọi hoạch toán"
    )
    @PostMapping("/v2/depositChecking")
    ResponseEntity<ResponseBase<String>> depositChecking(
        @RequestHeader("x-api-client") String clientId,
        @RequestHeader("x-api-validate") String signature,
        @RequestHeader("x-api-time") Long timestamp,
        @Valid @RequestBody EncryptedBodyRequest request);

    @Operation(
        summary = "Cập nhật trạng thái giao dịch",
        description = "- Cập nhật trạng thái giao dịch"
    )
    @PostMapping("/v2/notifyTransaction")
    ResponseEntity<ResponseBase<String>> notifyTransaction(
        @RequestHeader("x-api-client") String clientId,
        @RequestHeader("x-api-validate") String signature,
        @RequestHeader("x-api-time") Long timestamp,
        @Valid @RequestBody EncryptedBodyRequest request);

    /**
     *
     * @param clientId
     * @param request
     * @return
     */

    @Operation(
        summary = "Kiểm tra tài khoản ảo",
        description = "- Kiểm tra tài khoản ảo"
    )
    @PostMapping("/client/inquiryChecking")
    ResponseEntity<ResponseBase<InquiryCheckingClientResponse>> inquiryCheckingClient(
        @RequestHeader("x-api-client") String clientId,
        @Valid @RequestBody InquiryCheckingClientRequest request
    );

    @Operation(
        summary = "Kiểm tra trước khi gọi hoạch toán",
        description = "- Kiểm tra trước khi gọi hoạch toán"
    )
    @PostMapping("/client/depositChecking")
    ResponseEntity<ResponseBase<DepositCheckingClientResponse>> depositCheckingClient(
        @RequestHeader("x-api-client") String clientId,
        @Valid @RequestBody DepositCheckingClientRequest request
    );

    @Operation(
        summary = "Cập nhật trạng thái giao dịch",
        description = "- Cập nhật trạng thái giao dịch"
    )
    @PostMapping("/client/notifyTransaction")
    ResponseEntity<ResponseBase<NotifyTransactionClientResponse>> notifyTransactionClient(
        @RequestHeader("x-api-client") String clientId,
        @Valid @RequestBody NotifyTransactionClientRequest request);

}
