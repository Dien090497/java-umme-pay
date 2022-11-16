package vn.unicloud.umeepay.handler.merchant;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.merchant.request.CheckBankAccountRequest;
import vn.unicloud.umeepay.dtos.merchant.request.SubmitLinkAccountRequest;
import vn.unicloud.umeepay.dtos.merchant.response.CheckBankAccountResponse;
import vn.unicloud.umeepay.dtos.merchant.response.SubmitLinkAccountResponse;
import vn.unicloud.umeepay.dtos.user.response.CheckPhoneResponse;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.User;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.model.ChangePasswordCache;
import vn.unicloud.umeepay.service.MerchantService;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.service.UserService;
import vn.unicloud.umeepay.utils.CommonUtils;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

@Component
@Log4j2
@RequiredArgsConstructor
public class SubmitLinkAccountHandler extends RequestHandler<SubmitLinkAccountRequest, SubmitLinkAccountResponse> {

    private final MerchantService merchantService;

    private final UserService userService;

    private final RedisService redisService;

    @Value("${umeepay.change-password-timeout:900}")
    private int changePasswordTimeout;

    @Override
    public SubmitLinkAccountResponse handle(SubmitLinkAccountRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());
        // TODO: Call to core bank, OTP from bank

        CheckPhoneResponse response = userService.checkPhone(
            user.getPhone(),
            CommonUtils.buildLinkBankSignature(
                request.getBranch(),
                request.getAccountNo(),
                request.getAccountName()
            )
        );

        return new SubmitLinkAccountResponse(response.getSessionId());
    }
}
