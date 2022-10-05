package vn.unicloud.umeepay.dtos.payment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unicloud.umeepay.core.BaseResponseData;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncryptBodyResponse extends BaseResponseData {
    private String data;
}
