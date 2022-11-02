package vn.unicloud.umeepay.dtos.audit.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.enums.SystemModule;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuditVersionRequest extends BaseRequestData {

    @NonNull
    private SystemModule module;

}
