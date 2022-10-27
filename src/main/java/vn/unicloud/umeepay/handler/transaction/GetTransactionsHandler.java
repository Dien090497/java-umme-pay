package vn.unicloud.umeepay.handler.transaction;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.transaction.request.GetTransactionsRequest;
import vn.unicloud.umeepay.dtos.transaction.response.GetTransactionsResponse;
import vn.unicloud.umeepay.repository.TransactionRepository;

@Component
@RequiredArgsConstructor
public class GetTransactionsHandler extends RequestHandler<GetTransactionsRequest, GetTransactionsResponse> {

    private final TransactionRepository transactionRepository;
    @SneakyThrows
    @Override
    public GetTransactionsResponse handle(GetTransactionsRequest request) {

        return new GetTransactionsResponse(transactionRepository.findAll(request.getSpecification(), request.getPageable()));
    }
}
