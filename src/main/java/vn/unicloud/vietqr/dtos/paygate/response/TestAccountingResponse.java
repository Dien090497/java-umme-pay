package vn.unicloud.vietqr.dtos.paygate.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.vietqr.core.BaseResponseData;

@Data
@AllArgsConstructor
public class TestAccountingResponse extends BaseResponseData {

    private String data;

}
