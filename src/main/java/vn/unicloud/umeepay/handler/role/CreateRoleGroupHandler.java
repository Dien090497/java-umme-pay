package vn.unicloud.umeepay.handler.role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.role.request.CreateRoleGroupRequest;
import vn.unicloud.umeepay.dtos.role.response.RoleGroupResponse;
import vn.unicloud.umeepay.entity.Action;
import vn.unicloud.umeepay.entity.Permission;
import vn.unicloud.umeepay.entity.RoleGroup;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.ActionService;
import vn.unicloud.umeepay.service.RoleService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateRoleGroupHandler extends RequestHandler<CreateRoleGroupRequest, RoleGroupResponse> {

    private final RoleService roleService;

    private final ActionService actionService;

    @Override
    @Transactional
    public RoleGroupResponse handle(CreateRoleGroupRequest request) {
        if (roleService.getRoleByCode(request.getCode(), request.getScope()) != null) {
            throw new InternalException(ResponseCode.ROLE_ERROR_EXISTED_CODE);
        }

        RoleGroup role = new RoleGroup()
                .setScope(request.getScope())
                .setName(request.getName())
                .setCode(request.getCode())
                .setStatus(request.getStatus())
                .setDescription(request.getDescription());

       if (request.getActionIds() != null) {
           List<Action> actions = request.getActionIds()
                   .stream()
                   .map(id -> {
                       Action action = actionService.getById(id);
                       if (action == null) {
                           throw new InternalException(ResponseCode.ROLE_ERROR_ACTION_NOT_FOUND);
                       }
                       return action;
                   }).collect(Collectors.toList());
           role.setActions(actions);
       }

        if (roleService.saveRole(role) != null) {
            return new RoleGroupResponse(role);
        }

        throw new InternalException(ResponseCode.FAILED);
    }
}
