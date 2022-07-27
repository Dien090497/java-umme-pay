package vn.unicloud.vietqr.model;

import lombok.Data;
import vn.unicloud.vietqr.enums.TransactionStatus;

@Data
public class RecordFilter {

    private String keyword;
    private String branch;
    private TransactionStatus status;
    private String result;
    private String fromDate;
    private String toDate;

}
