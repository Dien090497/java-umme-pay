package vn.unicloud.umeepay.dtos.transaction.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import org.springframework.data.domain.Page;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.Transaction;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GetTransactionsResponse extends BaseResponseData {

    private Page<Transaction> transactions;

}
