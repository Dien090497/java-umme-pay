package vn.unicloud.umeepay.handler.merchant;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.merchant.request.SubmitLinkAccountRequest;
import vn.unicloud.umeepay.dtos.merchant.request.VerifyLinkAccountRequest;
import vn.unicloud.umeepay.dtos.merchant.response.BankAccountResponse;
import vn.unicloud.umeepay.dtos.merchant.response.SubmitLinkAccountResponse;
import vn.unicloud.umeepay.dtos.merchant.response.VerifyLinkAccountResponse;
import vn.unicloud.umeepay.dtos.user.response.CheckPhoneResponse;
import vn.unicloud.umeepay.entity.BankAccount;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.User;
import vn.unicloud.umeepay.enums.MerchantStatus;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.model.OTPKey;
import vn.unicloud.umeepay.repository.BankAccountRepository;
import vn.unicloud.umeepay.service.MerchantService;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.service.UserService;
import vn.unicloud.umeepay.utils.CommonUtils;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

import java.util.Objects;

@Component
@Log4j2
@RequiredArgsConstructor
public class VerifyLinkAccountHandler extends RequestHandler<VerifyLinkAccountRequest, VerifyLinkAccountResponse> {

    private final MerchantService merchantService;

    private final UserService userService;

    private final RedisService redisService;

    private final BankAccountRepository bankAccountRepository;

    @Override
    @Transactional
    public VerifyLinkAccountResponse handle(VerifyLinkAccountRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());
        // TODO: Call to core bank, check otp from bank
        String phoneKey = RedisKeyUtils.getOtpKey(user.getPhone());

        OTPKey otpKey = redisService.getValue(phoneKey, OTPKey.class);

        String signature = CommonUtils.buildLinkBankSignature(
            request.getBranch(),
            request.getAccountNo(),
            request.getAccountName()
        );

        if (otpKey == null ||
            !Objects.equals(otpKey.getPhone(), user.getPhone()) ||
            !Objects.equals(otpKey.getSignature(), signature) ||
            !Objects.equals(otpKey.getOtp(), request.getOtp())) {
            log.error("Invalid OTP");
            throw new InternalException(ResponseCode.OTP_INVALID);
        }

        if (!Objects.equals(otpKey.getSessionId(), request.getSessionId())) {
            log.error("Invalid session");
            throw new InternalException(ResponseCode.INVALID_SESSION);
        }

        redisService.deleteKey(phoneKey);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountName(request.getAccountName());
        bankAccount.setAccountNumber(request.getAccountNo());
        bankAccount.setBankType(request.getBranch());
        bankAccount.setMerchant(merchant);

        bankAccount = bankAccountRepository.save(bankAccount);

        if (merchant.getStatus().equals(MerchantStatus.SUBMIT_INFO)) {
            merchant.setStatus(MerchantStatus.LINKED_ACCOUNT);
        }
        merchantService.saveMerchant(merchant);

        return new VerifyLinkAccountResponse(bankAccount);
    }
}
