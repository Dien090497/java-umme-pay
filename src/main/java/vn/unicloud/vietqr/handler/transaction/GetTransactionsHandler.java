package vn.unicloud.vietqr.handler.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.unicloud.vietqr.core.RequestHandler;
import vn.unicloud.vietqr.dtos.transaction.request.GetTransactionsRequest;
import vn.unicloud.vietqr.dtos.transaction.response.GetTransactionsResponse;
import vn.unicloud.vietqr.repository.TransactionRepository;
import vn.unicloud.vietqr.service.TransactionService;

import java.time.LocalDate;

@Component
public class GetTransactionsHandler extends RequestHandler<GetTransactionsRequest, GetTransactionsResponse> {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public GetTransactionsResponse handle(GetTransactionsRequest request) {
        return new GetTransactionsResponse(transactionRepository.findAllByFilterKeyword(
            request.getPageable(),
            request.getKeyword(),
            request.getTraceId(),
            request.getTerminalId(),
            request.getStatus(),
            request.getFromDate() == null ? null : LocalDate.parse(request.getFromDate()),
            request.getToDate() == null ? null : LocalDate.parse(request.getToDate())
        ));
    }
}
