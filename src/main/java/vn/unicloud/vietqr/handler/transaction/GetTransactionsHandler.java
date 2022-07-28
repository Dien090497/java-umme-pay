package vn.unicloud.vietqr.handler.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.unicloud.vietqr.core.RequestHandler;
import vn.unicloud.vietqr.dtos.transaction.request.GetTransactionsRequest;
import vn.unicloud.vietqr.dtos.transaction.response.GetTransactionsResponse;
import vn.unicloud.vietqr.enums.TransactionStatus;
import vn.unicloud.vietqr.repository.TransactionRepository;
import vn.unicloud.vietqr.service.TransactionService;
import vn.unicloud.vietqr.utils.CommonUtils;

import java.time.LocalDate;

@Component
public class GetTransactionsHandler extends RequestHandler<GetTransactionsRequest, GetTransactionsResponse> {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public GetTransactionsResponse handle(GetTransactionsRequest request) {
        TransactionStatus status = CommonUtils.reformatStatus(request.getStatus());
        if (TransactionStatus.SUCCESS.equals(status)) {
            return new GetTransactionsResponse(transactionRepository.findAllBySuccess(
                request.getPageable(),
                request.getKeyword(),
                request.getTraceId(),
                request.getTerminalId(),
                status,
                request.getFromDate() == null ? null : LocalDate.parse(request.getFromDate()),
                request.getToDate() == null ? null : LocalDate.parse(request.getToDate()),
                TransactionStatus.SUCCESS
            ));
        } else {
            return new GetTransactionsResponse(transactionRepository.findAllByFail(
                request.getPageable(),
                request.getKeyword(),
                request.getTraceId(),
                request.getTerminalId(),
                status,
                request.getFromDate() == null ? null : LocalDate.parse(request.getFromDate()),
                request.getToDate() == null ? null : LocalDate.parse(request.getToDate()),
                TransactionStatus.SUCCESS
            ));
        }
    }
}
