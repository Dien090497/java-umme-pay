package vn.unicloud.vietqr.dtos.request;

import lombok.Data;
import vn.unicloud.vietqr.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateINickRequest extends BaseRequestData {

    @NotEmpty
    private String billId;

    private long amount;

    private String desc;

    private long timestamp;

}
