package vn.unicloud.umeepay.dtos.role.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetAllPermissionResponse extends BaseResponseData {

    private List<PermissionGroupResponse> permissionGroups;

}
