package vn.unicloud.umeepay.handler.ekyc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.ekyc.request.UploadCardRequest;
import vn.unicloud.umeepay.dtos.ekyc.response.EkycResponse;
import vn.unicloud.umeepay.entity.Ekyc;
import vn.unicloud.umeepay.entity.IdentifyInfo;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.Profile;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.EkycService;
import vn.unicloud.umeepay.service.MerchantService;
import vn.unicloud.umeepay.service.StorageService;

@Component
@Slf4j
@RequiredArgsConstructor
public class UploadCardHandler extends RequestHandler<UploadCardRequest, EkycResponse> {

    private final EkycService ekycService;

    private final MerchantService merchantService;

    private final StorageService storageService;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public EkycResponse handle(UploadCardRequest request) {
        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());

        Ekyc ekycResult = null;
        IdentifyInfo idInfo = null;
        try {
            switch (request.getEkycType()) {
                case REPRESENTATIVE_ID:
                    if (merchant.getProfile() == null) {
                        merchant.setProfile(new Profile());
                    }
                    ekycResult = ekycService.detectIdCard(request.getImageFront(), request.getImageBack());
                    idInfo = modelMapper.map(ekycResult, IdentifyInfo.class);

                    // Override previous EKYC information
                    Ekyc prevRepEkyc = merchant.getProfile().getRepEkyc();
                    if (prevRepEkyc != null && ekycResult != null) {
                        ekycResult.setId(prevRepEkyc.getId());
                    }

                    merchant.getProfile().setRepEkyc(ekycResult);
                    merchant.getProfile().setRepresentativeInfo(idInfo);
                    break;

                case OWNER_ID:
                    if (merchant.getProfile() == null) {
                        merchant.setProfile(new Profile());
                    }
                    ekycResult = ekycService.detectIdCard(request.getImageFront(), request.getImageBack());
                    idInfo = modelMapper.map(ekycResult, IdentifyInfo.class);

                    // Override previous EKYC information
                    Ekyc prevOwnerEkyc = merchant.getProfile().getOwnerEkyc();
                    if (prevOwnerEkyc != null && ekycResult != null) {
                        ekycResult.setId(prevOwnerEkyc.getId());
                    }

                    merchant.getProfile().setOwnerEkyc(ekycResult);
                    merchant.getProfile().setOwnerInfo(idInfo);
                    break;

                default:
                    break;
            }

            if (ekycResult == null || idInfo == null) {
                throw new InternalException(ResponseCode.FAILED);
            }
            String frontUrl = storageService.uploadFilePublic(request.getImageFront());
            String backUrl = storageService.uploadFilePublic(request.getImageBack());
            idInfo.setFrontUrl(frontUrl);
            idInfo.setBackUrl(backUrl);

            ekycResult.setFrontUrl(frontUrl);
            ekycResult.setBackUrl(frontUrl);

            Ekyc savedEkyc = ekycService.save(ekycResult);
            Merchant savedMerchant = merchantService.saveMerchant(merchant);

            if (savedMerchant != null && savedEkyc != null) {
                return new EkycResponse(savedEkyc);
            }
        } catch (Exception ex) {
            log.error("Detect ID card failed: {}", ex.getMessage());
        }
        throw new InternalException(ResponseCode.FAILED);
    }


}
