package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.BankAccountDto;
import vn.unicloud.umeepay.entity.BankAccount;
import vn.unicloud.umeepay.enums.Branch;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class CheckBankAccountResponse extends BaseResponseData {

    private Branch branch;
    private String accountNo;
    private String accountName;

    public CheckBankAccountResponse(Branch branch, String accountNo, String accountName) {
        this.branch = branch;
        this.accountName = accountName;
        this.accountNo = accountNo;
    }

}
