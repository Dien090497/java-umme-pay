package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.BankAccount;
import vn.unicloud.umeepay.enums.Branch;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountResponse extends BaseResponseData {

    private String id;

    private String accountNumber;

    private String accountName;

    private Branch bankType;

    public BankAccountResponse(BankAccount account) {
        if (account == null) {
            return;
        }

        this.id = account.getId();
        this.accountNumber = account.getAccountNumber();
        this.accountName = account.getAccountName();
        this.bankType = account.getBankType();
    }

}
