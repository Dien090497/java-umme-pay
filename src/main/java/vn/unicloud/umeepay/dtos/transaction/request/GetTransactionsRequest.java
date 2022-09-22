package vn.unicloud.umeepay.dtos.transaction.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Pageable;
import vn.unicloud.umeepay.core.BaseRequestData;

@Data
@SuperBuilder
public class GetTransactionsRequest extends BaseRequestData {
    private Pageable pageable;
    private String keyword;
    private String branch;
    private String status;
    private String result;
    private String fromDate;
    private String toDate;
    private String traceId;
    private String terminalId;
}
