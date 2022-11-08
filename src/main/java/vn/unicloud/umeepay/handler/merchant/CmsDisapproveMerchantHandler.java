package vn.unicloud.umeepay.handler.merchant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.merchant.request.CmsDisapproveMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.response.MerchantResponse;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.enums.MerchantStatus;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.AdminService;
import vn.unicloud.umeepay.service.ContextService;
import vn.unicloud.umeepay.service.MerchantService;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class CmsDisapproveMerchantHandler extends RequestHandler<CmsDisapproveMerchantRequest, MerchantResponse> {

    private final MerchantService merchantService;

    private final ContextService contextService;

    private final AdminService adminService;


    @Override
    @Transactional
    public MerchantResponse handle(CmsDisapproveMerchantRequest request) {
        Merchant merchant = merchantService.getMerchantById(request.getId());
        if (merchant == null) {
            throw new InternalException(ResponseCode.MERCHANT_NOT_FOUND);
        }

        /**
         * Chỉ từ chối duyệt khi merchant đã trải qua bước linked account
         */
        if (!MerchantStatus.LINKED_ACCOUNT.equals(merchant.getStatus())) {
            throw new InternalException(ResponseCode.INVALID_MERCHANT_STATUS);
        }

        /**
         * Get current admin
         */
        String approvedById = contextService.getLoggedInUserId().orElse(null);
        Administrator approvedBy = adminService.getById(approvedById);

        merchant.setStatus(MerchantStatus.DENIED)
                .setDisapprovedReason(request.getReason())
                .setApprovedBy(approvedBy)
                .setApprovedAt(LocalDateTime.now());

        try {
            if (merchantService.saveMerchant(merchant) != null) {
                return new MerchantResponse(merchant);
            }
        } catch (Exception ex) {
            log.error("Disapprove merchant failed, {}", ex.getMessage());
        }

        throw new InternalException(ResponseCode.FAILED);
    }
}
