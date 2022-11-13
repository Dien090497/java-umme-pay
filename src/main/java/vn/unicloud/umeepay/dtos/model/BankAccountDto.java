package vn.unicloud.umeepay.dtos.model;

import lombok.*;
import vn.unicloud.umeepay.entity.BankAccount;
import vn.unicloud.umeepay.enums.Branch;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {

    private String id;

    private String accountNo;

    private Branch bankType;

    private String accountName;

    public BankAccountDto(BankAccount bankAccount) {
        this.id = bankAccount.getId();
        this.accountNo = bankAccount.getAccountNumber();
        this.accountName = bankAccount.getAccountName();
        this.bankType = bankAccount.getBankType();
    }

}
