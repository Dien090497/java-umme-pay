package vn.unicloud.umeepay.dtos.role.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.PermissionGroup;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PermissionGroupResponse extends BaseResponseData {
    private Long id;
    private String name;
    private String description;
    private List<PermissionResponse> permissions;

    public PermissionGroupResponse(PermissionGroup permission) {
        if (permission == null) {
            return;
        }
        this.id = permission.getId();
        this.name = permission.getName();
        this.description = permission.getDescription();
        if (permission.getPermissions() != null) {
            this.permissions = permission.getPermissions()
                    .stream()
                    .map(action -> new PermissionResponse(action))
                    .collect(Collectors.toList());
        }
    }

}
