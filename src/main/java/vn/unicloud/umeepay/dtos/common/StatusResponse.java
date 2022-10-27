package vn.unicloud.umeepay.dtos.common;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse extends BaseResponseData {

    private boolean success;

}
