package vn.unicloud.umeepay.handler.paylink;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.paylink.request.GetPayLinkVietQRRequest;
import vn.unicloud.umeepay.dtos.paylink.request.QueryPayLinkRequest;
import vn.unicloud.umeepay.dtos.paylink.response.GetPayLinkVietQRResponse;
import vn.unicloud.umeepay.dtos.paylink.response.PayLinkStatusResponse;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.PayLink;
import vn.unicloud.umeepay.entity.Transaction;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.PayLinkRepository;
import vn.unicloud.umeepay.service.PayLinkService;
import vn.unicloud.umeepay.utils.CommonUtils;

@Component
@Slf4j
@RequiredArgsConstructor
public class QueryPayLinkHandler extends RequestHandler<QueryPayLinkRequest, PayLinkStatusResponse> {

    private final PayLinkRepository payLinkRepository;

    private final PayLinkService payLinkService;

    @Override
    public PayLinkStatusResponse handle(QueryPayLinkRequest request) {
        PayLink payLink = payLinkService.getPayLinkByCode(request.getPayLinkCode());

        Transaction transaction = payLink.getTransaction();
        if (transaction == null) {
            log.error("Transaction not found");
            throw new InternalException(ResponseCode.PAYLINK_INVALID);
        }
        return PayLinkStatusResponse.builder()
            .payLinkCode(payLink.getCode())
            .status(transaction.getStatus())
            .build();
    }
}
