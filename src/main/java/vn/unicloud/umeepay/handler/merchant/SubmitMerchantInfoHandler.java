package vn.unicloud.umeepay.handler.merchant;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.merchant.request.SubmitMerchantInfoRequest;
import vn.unicloud.umeepay.dtos.merchant.response.SubmitProfileInfoResponse;
import vn.unicloud.umeepay.dtos.model.EnterpriseMerchantDto;
import vn.unicloud.umeepay.dtos.model.MerchantDto;
import vn.unicloud.umeepay.dtos.model.PersonalMerchantDto;
import vn.unicloud.umeepay.entity.Ekyc;
import vn.unicloud.umeepay.entity.IdentifyInfo;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.Profile;
import vn.unicloud.umeepay.enums.BusinessType;
import vn.unicloud.umeepay.enums.MerchantStatus;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.ProfileRepository;
import vn.unicloud.umeepay.service.MerchantService;

@Component
@Log4j2
@RequiredArgsConstructor
public class SubmitMerchantInfoHandler extends RequestHandler<SubmitMerchantInfoRequest, SubmitProfileInfoResponse> {

    private final MerchantService merchantService;

    private final ProfileRepository profileRepository;

    @Override
    public SubmitProfileInfoResponse handle(SubmitMerchantInfoRequest request) {
        // Validate các field
        // Update thông tin chung
        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());
        if (merchant == null) {
            throw new InternalException(ResponseCode.MERCHANT_NOT_FOUND);
        }
        if (!merchant.getStatus().equals(MerchantStatus.CREATED) &&
            !merchant.getStatus().equals(MerchantStatus.LINKED_ACCOUNT)) {
            log.error("Invalid merchant state: {} to submit info", merchant.getStatus());
            throw new InternalException(ResponseCode.INVALID_MERCHANT_STATUS);
        }

        Profile profile = merchant.getProfile();
        if (profile == null) {
            log.debug("Not found profile, create new");
            profile = new Profile();
        }
        profile.setBusinessType(request.getBusinessType());
        profile.setBusinessSectors(request.getBusinessProducts());
        profile.setBusinessItems(request.getProduct());
        profile.setRevenueType(request.getRevenueType());
        profile.setContactEmail(request.getEmail());
        profile.setWebsiteUrl(request.getWebsite());
        profile.setStoreAddress(request.getAddress());

        if (BusinessType.ORGANIZATION.equals(request.getBusinessType())) {
            EnterpriseMerchantDto enterprise = request.getEnterpriseMerchant();
            if (enterprise == null) {
                log.error("Invalid enterprise info");
                throw new InternalException(ResponseCode.INVALID_ENTERPRISE_INFO);
            }
            // fixme
            Ekyc ownerEkyc = new Ekyc();
            ownerEkyc.setFrontUrl(enterprise.getOwnerFrontUrl());
            ownerEkyc.setBackUrl(enterprise.getOwnerbackUrl());

            IdentifyInfo ownerInfo = new IdentifyInfo();

            profile.setOwnerEkyc(ownerEkyc);
            profile.setOwnerInfo(ownerInfo);

            Ekyc preEkyc = new Ekyc();
            preEkyc.setFrontUrl(enterprise.getRepresentFrontUrl());
            preEkyc.setBackUrl(enterprise.getRepresentBackUrl());

            IdentifyInfo preInfo = new IdentifyInfo();

            profile.setRepEkyc(preEkyc);
            profile.setRepresentativeInfo(preInfo);
        } else if (BusinessType.PERSONAL.equals(request.getBusinessType())) {
            PersonalMerchantDto personal = request.getPersonalMerchant();
            if (personal == null) {
                log.error("Invalid personal merchant info");
                throw new InternalException(ResponseCode.INVALID_PERSONAL_INFO);
            }
            profile.setCompanyName(personal.getCompanyName());
            profile.setCompanyAddress(personal.getAddress());
            profile.setCompanyPhone(personal.getPhone());

            // fixme
            Ekyc ownerEkyc = new Ekyc();
            ownerEkyc.setFrontUrl(personal.getFrontUrl());
            ownerEkyc.setBackUrl(personal.getBackUrl());

            IdentifyInfo ownerInfo = new IdentifyInfo();

            profile.setOwnerEkyc(ownerEkyc);
            profile.setOwnerInfo(ownerInfo);
        }

        profile = profileRepository.save(profile);

        merchant.setName(request.getName());
        merchant.setProfile(profile);
        if (merchant.getStatus().equals(MerchantStatus.CREATED)) {
            merchant.setStatus(MerchantStatus.SUBMIT_INFO);
        }
        merchantService.saveMerchant(merchant);

        // Update thông tin nếu là doanh nghiệp / cá nhân
        // Update thông tin giấy tờ
        return new SubmitProfileInfoResponse(new MerchantDto(merchant));
    }
}
