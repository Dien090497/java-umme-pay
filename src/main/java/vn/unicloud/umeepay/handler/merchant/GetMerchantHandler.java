package vn.unicloud.umeepay.handler.merchant;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.merchant.request.GetMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.response.GetMerchantResponse;
import vn.unicloud.umeepay.service.MerchantService;

@Component
@Log4j2
@RequiredArgsConstructor
public class GetMerchantHandler extends RequestHandler<GetMerchantRequest, GetMerchantResponse> {

    private final MerchantService merchantService;

    @Override
    public GetMerchantResponse handle(GetMerchantRequest request) {
        return merchantService.getMerchant(request);
    }
}
