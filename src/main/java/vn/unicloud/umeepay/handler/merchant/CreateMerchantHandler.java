package vn.unicloud.umeepay.handler.merchant;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.merchant.request.CreateMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.response.CreateMerchantResponse;
import vn.unicloud.umeepay.dtos.request.LoginRequest;
import vn.unicloud.umeepay.dtos.response.AccessTokenResponseCustom;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.MerchantRepository;
import vn.unicloud.umeepay.service.KeycloakService;
import vn.unicloud.umeepay.service.MerchantService;

@Component
@Log4j2
@RequiredArgsConstructor
public class CreateMerchantHandler extends RequestHandler<CreateMerchantRequest, CreateMerchantResponse> {

    private final MerchantService merchantService;

    @Override
    public CreateMerchantResponse handle(CreateMerchantRequest request) {
        return merchantService.createMerchant(request);
    }
}
