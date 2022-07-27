package vn.unicloud.vietqr.handler.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.unicloud.vietqr.core.RequestHandler;
import vn.unicloud.vietqr.dtos.paygate.request.TestAccountingRequest;
import vn.unicloud.vietqr.dtos.paygate.response.TestAccountingResponse;
import vn.unicloud.vietqr.dtos.transaction.request.GetTransactionsRequest;
import vn.unicloud.vietqr.dtos.transaction.response.GetTransactionsResponse;
import vn.unicloud.vietqr.model.RecordFilter;
import vn.unicloud.vietqr.repository.TransactionRepository;
import vn.unicloud.vietqr.repository.TransactionTemplateRepository;
import vn.unicloud.vietqr.service.TransactionService;
import vn.unicloud.vietqr.utils.ModelMapperUtils;

@Component
public class GetTransactionsHandler extends RequestHandler<GetTransactionsRequest, GetTransactionsResponse> {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionTemplateRepository templateRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public GetTransactionsResponse handle(GetTransactionsRequest request) {
        RecordFilter callRecordFilter = ModelMapperUtils.mapper(request, RecordFilter.class);
        return new GetTransactionsResponse(transactionRepository.findAllByFilterKeyword(
            request.getPageable(),
            callRecordFilter.getKeyword()
        ));
    }
}
