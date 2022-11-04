package vn.unicloud.umeepay.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.unicloud.umeepay.core.BaseResponseData;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncryptBodyResponse extends BaseResponseData {
    private String data;
}
