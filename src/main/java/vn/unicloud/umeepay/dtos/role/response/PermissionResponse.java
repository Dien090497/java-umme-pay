package vn.unicloud.umeepay.dtos.role.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.Action;
import vn.unicloud.umeepay.entity.Permission;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponse extends BaseResponseData {
    private Long id;
    private String name;
    private String description;

    private List<Action> actions;

    public PermissionResponse(Permission permission) {
        if (permission == null) {
            return;
        }
        this.id = permission.getId();
        this.name = permission.getName();
        this.description = permission.getDescription();
        this.actions = permission.getActions();
    }
}
