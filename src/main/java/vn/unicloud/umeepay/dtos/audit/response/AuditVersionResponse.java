package vn.unicloud.umeepay.dtos.audit.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuditVersionResponse extends BaseResponseData {

    private Integer version;

}
