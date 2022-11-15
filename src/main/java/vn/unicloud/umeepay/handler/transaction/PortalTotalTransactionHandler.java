package vn.unicloud.umeepay.handler.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.transaction.request.PortalTotalTransactionRequest;
import vn.unicloud.umeepay.dtos.transaction.response.PortalTotalTransactionResponse;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.MerchantService;
import vn.unicloud.umeepay.service.TransactionService;

@Component
@RequiredArgsConstructor
public class PortalTotalTransactionHandler extends RequestHandler<PortalTotalTransactionRequest, PortalTotalTransactionResponse> {

    private final TransactionService transactionService;

    private final MerchantService merchantService;

    @Override
    public PortalTotalTransactionResponse handle(PortalTotalTransactionRequest request) {
        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());
        if (merchant == null) {
            throw new InternalException(ResponseCode.MERCHANT_NOT_FOUND);
        }

        PortalTotalTransactionResponse response = new PortalTotalTransactionResponse()
                .setTotal(transactionService.getTotalTransaction(merchant, request.getDate()))
                .setAmount(transactionService.getTotalTransactionAmount(merchant, request.getDate()));

        return response;

    }
}
