package vn.unicloud.umeepay.dtos.payment.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import lombok.NoArgsConstructor;
import vn.unicloud.umeepay.core.BaseResponseData;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EncryptBodyResponse extends BaseResponseData {
    private String data;
}
