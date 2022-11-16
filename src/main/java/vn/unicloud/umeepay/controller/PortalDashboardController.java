package vn.unicloud.umeepay.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IPortalDashboardController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.transaction.request.PortalTotalTransactionRequest;
import vn.unicloud.umeepay.dtos.transaction.request.PortalTransactionStatisticRequest;
import vn.unicloud.umeepay.dtos.transaction.response.PortalTotalTransactionResponse;
import vn.unicloud.umeepay.dtos.transaction.response.PortalTransactionStatisticResponse;
import vn.unicloud.umeepay.service.SecurityService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class PortalDashboardController extends BaseController implements IPortalDashboardController {

    private final SecurityService securityService;

    @Override
    public ResponseEntity<ResponseBase<PortalTotalTransactionResponse>> getTotalTransaction() {
        PortalTotalTransactionRequest request = new PortalTotalTransactionRequest();
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<PortalTotalTransactionResponse>> getTotalTodayTransactions() {
        PortalTotalTransactionRequest request = new PortalTotalTransactionRequest();
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        request.setDate(LocalDate.now());
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<PortalTransactionStatisticResponse>> getTransactionStatistic(LocalDate from, LocalDate to) {
        PortalTransactionStatisticRequest request = new PortalTransactionStatisticRequest();
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        request.setFrom(from);
        request.setTo(to);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<PortalTotalTransactionResponse>> getTotalTransactionFromBank() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseBase<PortalTotalTransactionResponse>> getTotalTodayTransactionsFromBank() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseBase<PortalTransactionStatisticResponse>> getTransactionStatisticFromBank(LocalDate from, LocalDate to) {
        return null;
    }
}
