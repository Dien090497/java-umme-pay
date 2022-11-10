package vn.unicloud.umeepay.handler.ekyc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.ekyc.request.DetectCardRequest;
import vn.unicloud.umeepay.dtos.ekyc.response.EkycResponse;
import vn.unicloud.umeepay.entity.Ekyc;
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
public class DetectCardHandler extends RequestHandler<DetectCardRequest, EkycResponse> {

    private final EkycService ekycService;

    private final MerchantService merchantService;
    private final StorageService storageService;

    @Override
    @Transactional
    public EkycResponse handle(DetectCardRequest request) {
        Merchant merchant = merchantService.getMerchantById(request.getMerchantId());
        if (merchant == null) {
            throw new InternalException(ResponseCode.MERCHANT_NOT_FOUND);
        }

        Ekyc ekycResult = ekycService.detectCard(request.getImageFront(), request.getImageBack());
        if (ekycResult == null) {
            throw new InternalException(ResponseCode.FAILED);
        }

        try {
            String frontUrl = storageService.uploadFile(request.getImageFront());
            String backUrl = storageService.uploadFile(request.getImageBack());

            ekycResult.setFrontUrl(frontUrl);
            ekycResult.setBackUrl(backUrl);

            Ekyc saved = ekycService.save(ekycResult);

            switch (request.getEkycType()) {
                case REPRESENTATIVE_ID:
                    if (merchant.getProfile() == null) {
                        merchant.setProfile(new Profile());
                    }
                    merchant.getProfile().setRepEkyc(ekycResult);
                    break;
                case OWNER_ID:
                    if (merchant.getProfile() == null) {
                        merchant.setProfile(new Profile());
                    }
                    merchant.getProfile().setOwnerEkyc(ekycResult);
                    break;

                default:
                    break;

            }

            if (merchantService.saveMerchant(merchant) != null) {
                return new EkycResponse(saved);
            }
        } catch (Exception ex) {
            log.error("Detect ID card failed: {}", ex.getMessage());
        }
        throw new InternalException(ResponseCode.FAILED);
    }


}
