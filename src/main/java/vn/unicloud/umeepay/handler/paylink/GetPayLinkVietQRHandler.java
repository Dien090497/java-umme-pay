package vn.unicloud.umeepay.handler.paylink;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.dtos.paylink.request.GetPayLinkRequest;
import vn.unicloud.umeepay.dtos.paylink.request.GetPayLinkVietQRRequest;
import vn.unicloud.umeepay.dtos.paylink.response.GetPayLinkVietQRResponse;
import vn.unicloud.umeepay.dtos.paylink.response.PayLinkResponse;
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
public class GetPayLinkVietQRHandler extends RequestHandler<GetPayLinkVietQRRequest, GetPayLinkVietQRResponse> {

    private final PayLinkRepository payLinkRepository;

    private final PayLinkService payLinkService;

    @Override
    public GetPayLinkVietQRResponse handle(GetPayLinkVietQRRequest request) {
        PayLink payLink = payLinkService.getPayLinkByCode(request.getPayLinkCode());

        Transaction transaction = payLink.getTransaction();
        if (transaction == null) {
            log.error("Transaction not found");
            throw new InternalException(ResponseCode.PAYLINK_INVALID);
        }

        String qrCode = CommonUtils.generateQRCode(
            CommonUtils.getBinByBankType(transaction.getBankType()),
            transaction.getVirtualAccount(),
            transaction.getAmount(),
            transaction.getDescription()
        );

        Merchant merchant = payLink.getMerchant();
        if (merchant == null) {
            log.error("Merchant not found");
            throw new InternalException(ResponseCode.PAYLINK_INVALID);
        }

        return GetPayLinkVietQRResponse.builder()
            .shopName(merchant.getName())
            .merchantCode(merchant.getMerchantCode())
            .qrContent(qrCode)
            .amount(transaction.getAmount())
            .description(payLink.getDescription())
            .successUrl(transaction.getSuccessUrl())
            .failUrl(transaction.getFailUrl())
            .redirectAfter(transaction.getCallbackAfter())
            .status(transaction.getStatus())
            .build();
    }
}
