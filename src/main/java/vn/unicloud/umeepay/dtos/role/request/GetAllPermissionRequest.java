package vn.unicloud.umeepay.dtos.role.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.enums.RoleType;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPermissionRequest extends BaseRequestData {

    private RoleType scope;

}
