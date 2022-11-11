package vn.unicloud.umeepay.handler.merchant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.merchant.request.CmsGetMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.response.MerchantDetailResponse;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.MerchantService;

@Component
@RequiredArgsConstructor
public class CmsGetMerchantHandler extends RequestHandler<CmsGetMerchantRequest, MerchantDetailResponse> {

    private final MerchantService merchantService;

    @Override
    public MerchantDetailResponse handle(CmsGetMerchantRequest request) {

        Merchant merchant = merchantService.getMerchantById(request.getId());
        if (merchant == null) {
            throw new InternalException(ResponseCode.MERCHANT_NOT_FOUND);
        }
        return new MerchantDetailResponse(merchant);
    }
}
