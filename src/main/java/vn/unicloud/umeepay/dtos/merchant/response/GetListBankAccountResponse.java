package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.BankAccountDto;
import vn.unicloud.umeepay.dtos.model.MerchantDto;
import vn.unicloud.umeepay.entity.BankAccount;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class GetListBankAccountResponse extends BaseResponseData {

    private List<BankAccountDto> bankAccounts;

    public GetListBankAccountResponse(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts.stream().map(BankAccountDto::new).collect(Collectors.toList());
    }

}
