package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.unicloud.umeepay.config.OpenApiConfig;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.transaction.response.PortalTotalTransactionResponse;
import vn.unicloud.umeepay.dtos.transaction.response.PortalTransactionStatisticResponse;

import java.time.LocalDate;


@Tag(name = "[Portal] Dashboard controller", description = "Thao tác với Portal Dashboard")
@RequestMapping(value = "/api/dashboard/portal")
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public interface IPortalDashboardController {

    @Operation(summary = "Lấy tổng số giao dịch và tổng tiền trên hệ thống UMEEPAY")
    @GetMapping("/v1/transaction/total")
    ResponseEntity<ResponseBase<PortalTotalTransactionResponse>> getTotalTransaction();

    @Operation(summary = "Lấy tổng số giao dịch và tổng tiền ngày hôm nay trên hệ thống UMEEPAY")
    @GetMapping("/v1/transaction/total/today")
    ResponseEntity<ResponseBase<PortalTotalTransactionResponse>> getTotalTodayTransactions();


    @Operation(summary = "Thống kê biểu đồ giao dịch Trên hệ thống UmeePay")
    @GetMapping("/v1/transaction/statistic")
    ResponseEntity<ResponseBase<PortalTransactionStatisticResponse>> getTransactionStatistic(
            @RequestParam(required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate from,
            @RequestParam(required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate to);


    @Operation(summary = "Lấy tổng số giao dịch và tổng tiền từ BANK")
    @GetMapping("/v1/bankTransaction/total")
    ResponseEntity<ResponseBase<PortalTotalTransactionResponse>> getTotalTransactionFromBank();

    @Operation(summary = "Lấy tổng số giao dịch và tổng tiền ngày hôm nay từ BANK")
    @GetMapping("/v1/bankTransaction/total/today")
    ResponseEntity<ResponseBase<PortalTotalTransactionResponse>> getTotalTodayTransactionsFromBank();

    @Operation(summary = "Thống kê biểu đồ giao dịch Trên hệ thống từ BANK")
    @GetMapping("/v1/bankTransaction/statistic")
    ResponseEntity<ResponseBase<PortalTransactionStatisticResponse>> getTransactionStatisticFromBank(
            @RequestParam(required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate from,
            @RequestParam(required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate to);
}
