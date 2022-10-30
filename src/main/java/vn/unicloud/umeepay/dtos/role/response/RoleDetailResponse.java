package vn.unicloud.umeepay.dtos.role.response;


import lombok.*;
import vn.unicloud.umeepay.entity.Role;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoleDetailResponse extends RoleResponse {

    private List<ActionResponse> actions;

    public RoleDetailResponse(Role role) {
        super(role);
        if (role == null) {
            return;
        }
        this.actions = role.getActions()
                .stream()
                .map(action -> new ActionResponse(action))
                .collect(Collectors.toList());
    }
}
