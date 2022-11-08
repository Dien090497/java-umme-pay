package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.dtos.user.response.UserResponse;
import vn.unicloud.umeepay.entity.Merchant;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class MerchantDetailResponse extends MerchantResponse {

    private ProfileResponse profile;

    private BankAccountResponse bankAccount;

    private UserResponse owner;

    public MerchantDetailResponse(Merchant merchant) {
        super(merchant);
        if (merchant == null) {
            return;
        }

        this.owner = merchant.getUser() != null
                ? new UserResponse(merchant.getUser())
                : null;

        this.profile = merchant.getProfile() != null
                ? new ProfileResponse(merchant.getProfile())
                : null;

        this.bankAccount = merchant.getBankAccount() != null
                ? new BankAccountResponse(merchant.getBankAccount())
                : null;
    }
}
