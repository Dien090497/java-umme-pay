package vn.unicloud.umeepay.handler.merchant;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.merchant.request.CreateMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.request.UpdateMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.response.CreateMerchantResponse;
import vn.unicloud.umeepay.dtos.merchant.response.UpdateMerchantResponse;
import vn.unicloud.umeepay.service.MerchantService;

@Component
@Log4j2
@RequiredArgsConstructor
public class UpdateMerchantHandler extends RequestHandler<UpdateMerchantRequest, UpdateMerchantResponse> {

    private final MerchantService merchantService;

    @Override
    public UpdateMerchantResponse handle(UpdateMerchantRequest request) {
        return merchantService.updateMerchant(request);
    }
}
