package vn.unicloud.umeepay.handler.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.transaction.request.GetTransactionsRequest;
import vn.unicloud.umeepay.dtos.transaction.response.GetTransactionsResponse;
import vn.unicloud.umeepay.enums.TransactionStatus;
import vn.unicloud.umeepay.repository.TransactionRepository;
import vn.unicloud.umeepay.service.TransactionService;
import vn.unicloud.umeepay.utils.CommonUtils;

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
