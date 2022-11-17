package vn.unicloud.umeepay.dtos.audit.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.enums.SystemModule;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuditVersionRequest extends BaseRequestData {

    @NotNull
    private SystemModule module;

}
