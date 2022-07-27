package vn.unicloud.vietqr.dtos.transaction.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import vn.unicloud.vietqr.core.BaseResponseData;
import vn.unicloud.vietqr.entity.Transaction;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
public class GetTransactionsResponse extends BaseResponseData {

    @NotEmpty
    private Page<Transaction> transactions;

}
