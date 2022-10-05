package vn.unicloud.umeepay.dtos.transaction.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.Transaction;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class GetTransactionsResponse extends BaseResponseData {

    private Page<Transaction> transactions;

}
