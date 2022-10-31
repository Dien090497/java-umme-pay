package vn.unicloud.umeepay.dtos.role.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.Permission;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PermissionResponse extends BaseResponseData {
    private Long id;
    private String name;
    private String description;
    private List<ActionResponse> actions;

    public PermissionResponse(Permission permission) {
        if (permission == null) {
            return;
        }
        this.id = permission.getId();
        this.name = permission.getName();
        this.description = permission.getDescription();
        if (permission.getActions() != null) {
            this.actions = permission.getActions()
                    .stream()
                    .map(action -> new ActionResponse(action))
                    .collect(Collectors.toList());
        }
    }

}
