package vn.unicloud.umeepay.handler.merchant;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.merchant.request.GetListBankAccountRequest;
import vn.unicloud.umeepay.dtos.merchant.request.SubmitMerchantInfoRequest;
import vn.unicloud.umeepay.dtos.merchant.response.GetListBankAccountResponse;
import vn.unicloud.umeepay.dtos.merchant.response.SubmitProfileInfoResponse;
import vn.unicloud.umeepay.dtos.model.EnterpriseMerchantDto;
import vn.unicloud.umeepay.dtos.model.MerchantDto;
import vn.unicloud.umeepay.dtos.model.PersonalMerchantDto;
import vn.unicloud.umeepay.entity.Ekyc;
import vn.unicloud.umeepay.entity.IdentifyInfo;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.Profile;
import vn.unicloud.umeepay.enums.BusinessType;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.ProfileRepository;
import vn.unicloud.umeepay.service.MerchantService;

@Component
@Log4j2
@RequiredArgsConstructor
public class GetListBankAccountHandler extends RequestHandler<GetListBankAccountRequest, GetListBankAccountResponse> {

    private final MerchantService merchantService;

    @Override
    public GetListBankAccountResponse handle(GetListBankAccountRequest request) {
        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());
        return new GetListBankAccountResponse(merchant.getBankAccounts());
    }
}
