package vn.unicloud.umeepay.dtos.role.response;


import lombok.*;
import vn.unicloud.umeepay.dtos.admin.response.AdminResponse;
import vn.unicloud.umeepay.dtos.user.response.UserResponse;
import vn.unicloud.umeepay.entity.Action;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.entity.RoleGroup;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoleGroupDetailResponse extends RoleGroupResponse {

    private List<Action> actions;

    private boolean currentUsed;

    public RoleGroupDetailResponse(RoleGroup role) {
        super(role);
        if (role == null) {
            return;
        }
        this.actions = role.getActions();
    }
}
