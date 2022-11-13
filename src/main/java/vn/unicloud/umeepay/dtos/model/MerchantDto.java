package vn.unicloud.umeepay.dtos.model;

import lombok.*;
import vn.unicloud.umeepay.entity.BankAccount;
import vn.unicloud.umeepay.entity.Credential;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.Profile;
import vn.unicloud.umeepay.enums.MerchantStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDto {

    private String id;

    private MerchantStatus status;

    private CredentialDto credential;

    private String merchantCode;

    private LocalDateTime createDateTime;

    private String name;

    private String accountNo;

    private ProfileDto profile;

    private List<BankAccountDto> bankAccounts;

    public MerchantDto(Merchant merchant) {
        this.id = merchant.getId();
        this.merchantCode = merchant.getMerchantCode();
        this.status = merchant.getStatus();
        Credential credentialEn = merchant.getCredential();
        if (credentialEn != null) {
            this.credential = new CredentialDto(credentialEn);
        }
        this.createDateTime = merchant.getCreatedAt();
        this.name = merchant.getName();
        this.accountNo = merchant.getAccountNo();
        Profile profileEn = merchant.getProfile();
        if (profileEn != null) {
            this.profile = new ProfileDto(profileEn);
        }
        List<BankAccount> bankAccountsEn = merchant.getBankAccounts();
        if (bankAccountsEn != null) {
            this.bankAccounts = bankAccountsEn.stream().map(BankAccountDto::new).collect(Collectors.toList());
        }
    }

}
