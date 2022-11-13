package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.dtos.model.BankAccountDto;
import vn.unicloud.umeepay.dtos.user.response.UserResponse;
import vn.unicloud.umeepay.entity.BankAccount;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.Profile;
import vn.unicloud.umeepay.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class MerchantDetailResponse extends MerchantResponse {

    private ProfileResponse profile;

    private List<BankAccountDto> bankAccounts;

    private UserResponse owner;

    public MerchantDetailResponse(Merchant merchant) {
        super(merchant);
        if (merchant == null) {
            return;
        }

        User user = merchant.getUser();
        this.owner = user != null
                ? new UserResponse(user)
                : null;

        Profile profile = merchant.getProfile();
        this.profile = profile != null
                ? new ProfileResponse(profile)
                : null;

        List<BankAccount> bankAccount = merchant.getBankAccounts();
        this.bankAccounts = bankAccount != null
                ? bankAccount.stream().map(BankAccountDto::new).collect(Collectors.toList())
                : null;
    }
}
