package vn.unicloud.umeepay.handler.merchant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.merchant.request.CmsBlockMerchantMemberRequest;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.User;
import vn.unicloud.umeepay.enums.MerchantStatus;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.UserStatus;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.MerchantService;
import vn.unicloud.umeepay.service.UserService;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CmsBlockMerchantMemberHandler extends RequestHandler<CmsBlockMerchantMemberRequest, StatusResponse> {

    private final MerchantService merchantService;

    private final UserService userService;

    @Override
    @Transactional
    public StatusResponse handle(CmsBlockMerchantMemberRequest request) {
        // Get blocked user
        User user = userService.getUserById(request.getMemberId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }

        if (!UserStatus.ACTIVE.equals(user.getStatus())) {
            throw new InternalException(ResponseCode.INVALID_USER_STATUS);
        }

        /**
         * Get merchant of blocked user
         * if user is merchant owner, then blocked merchant and all the rest members of this merchant
         */
        Merchant merchant = merchantService.getMerchantByUserId(user.getId());
        if (merchant == null) {
            log.error("There is no merchant for user: {}", user.getId());
            throw new InternalException(ResponseCode.FAILED);
        }

        boolean isOwner = user.getId().equals(merchant.getUser().getId());
        if (isOwner) {
            merchant.getMembers().stream().forEach(member -> {
                if (UserStatus.INACTIVE.equals(member.getStatus())) {
                    return;
                }
                member.setStatus(UserStatus.INACTIVE);
                member.setBlockedCluster(true);
            });

            // Update merchant status
            MerchantStatus currentStatus = merchant.getStatus();
            merchant.setPreviousStatus(currentStatus);
            merchant.setStatus(MerchantStatus.INACTIVE);

            if (merchantService.saveMerchant(merchant) == null) {
                throw new InternalException(ResponseCode.FAILED);
            }
        }

        user.setStatus(UserStatus.INACTIVE);
        if (userService.saveUser(user) != null) {
            return new StatusResponse(true);
        }

        throw new InternalException(ResponseCode.FAILED);

    }
}
