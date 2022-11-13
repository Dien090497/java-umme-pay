package vn.unicloud.umeepay.handler.merchant;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.merchant.request.CheckBankAccountRequest;
import vn.unicloud.umeepay.dtos.merchant.request.GetListBankAccountRequest;
import vn.unicloud.umeepay.dtos.merchant.response.CheckBankAccountResponse;
import vn.unicloud.umeepay.dtos.merchant.response.GetListBankAccountResponse;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.MerchantService;

@Component
@Log4j2
@RequiredArgsConstructor
public class CheckLinkAccountHandler extends RequestHandler<CheckBankAccountRequest, CheckBankAccountResponse> {

    private final MerchantService merchantService;

    @Override
    public CheckBankAccountResponse handle(CheckBankAccountRequest request) {
        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());
        if (merchant == null) {
            throw new InternalException(ResponseCode.MERCHANT_NOT_FOUND);
        }
        // TODO: Call to core bank
        return new CheckBankAccountResponse(
            request.getBranch(),
            request.getAccountNo(),
            "NGUYEN VAN TEST"
        );
    }
}
