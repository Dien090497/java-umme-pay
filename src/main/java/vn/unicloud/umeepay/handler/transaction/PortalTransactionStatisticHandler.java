package vn.unicloud.umeepay.handler.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.transaction.request.PortalTransactionStatisticRequest;
import vn.unicloud.umeepay.dtos.transaction.response.PortalTransactionStatisticResponse;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.enums.PaymentType;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.MerchantService;
import vn.unicloud.umeepay.service.TransactionService;

import java.time.LocalDate;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PortalTransactionStatisticHandler extends RequestHandler<PortalTransactionStatisticRequest, PortalTransactionStatisticResponse> {

    private final TransactionService transactionService;

    private final MerchantService merchantService;

    @Override
    public PortalTransactionStatisticResponse handle(PortalTransactionStatisticRequest request) {

        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());
        if (merchant == null) {
            throw new InternalException(ResponseCode.MERCHANT_NOT_FOUND);
        }

        Map<LocalDate, Map<PaymentType, Long>> statistics = transactionService.getTransactionStatistic(merchant, request.getFrom(), request.getTo());
        return new PortalTransactionStatisticResponse(statistics);
    }
}
