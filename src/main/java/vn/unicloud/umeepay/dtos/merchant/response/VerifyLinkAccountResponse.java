package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.BankAccountDto;
import vn.unicloud.umeepay.entity.BankAccount;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class VerifyLinkAccountResponse extends BaseResponseData {

    private BankAccountDto bankAccount;

    public VerifyLinkAccountResponse(BankAccount bankAccount) {
        this.bankAccount = new BankAccountDto(bankAccount);
    }

}
