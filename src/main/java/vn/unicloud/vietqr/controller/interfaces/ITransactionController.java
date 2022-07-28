package vn.unicloud.vietqr.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.unicloud.vietqr.core.ResponseBase;
import vn.unicloud.vietqr.dtos.transaction.response.GetTransactionsResponse;

@Tag(name = "Transaction Controller", description = "Thao tác với transaction")
@RequestMapping(value = "/api/transaction")
public interface ITransactionController {

    @Operation(
        summary = "Get toàn bộ transaction",
        description = "- Get toàn bộ transaction",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
        })
    @RequestMapping(value = "/v1/getAll", method = RequestMethod.GET)
    ResponseEntity<ResponseBase<GetTransactionsResponse>> getAll(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer size,
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String terminalId,
        @RequestParam(required = false) String traceId,
        @RequestParam(required = false) String branch,
        @RequestParam(required = false) String status,
        @RequestParam(required = false) String fromDate,
        @RequestParam(required = false) String toDate
    );

    @Operation(
        summary = "Download giao dịch",
        description = "- Download giao dịch",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
        })
    @RequestMapping(value = "/v1/download", method = RequestMethod.GET)
    ResponseEntity<?> download(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer size,
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String terminalId,
        @RequestParam(required = false) String traceId,
        @RequestParam(required = false) String branch,
        @RequestParam(required = false) String status,
        @RequestParam(required = false) String fromDate,
        @RequestParam(required = false) String toDate
    );
}
