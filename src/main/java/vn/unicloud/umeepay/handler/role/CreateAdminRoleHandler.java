package vn.unicloud.umeepay.handler.role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.role.request.CreateRoleRequest;
import vn.unicloud.umeepay.dtos.role.response.RoleResponse;
import vn.unicloud.umeepay.entity.Action;
import vn.unicloud.umeepay.entity.Role;
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
public class CreateAdminRoleHandler extends RequestHandler<CreateRoleRequest, RoleResponse> {

    private final RoleService roleService;

    private final ActionService actionService;

    @Override
    @Transactional
    public RoleResponse handle(CreateRoleRequest request) {
        if (roleService.getRoleByCode(request.getCode(), request.getScope()) != null) {
            throw new InternalException(ResponseCode.ROLE_ERROR_EXISTED_CODE);
        }

        Role role = new Role()
                .setScope(request.getScope())
                .setName(request.getName())
                .setCode(request.getCode())
                .setStatus(request.getStatus())
                .setDescription(request.getDescription());

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

        if (roleService.saveRole(role) != null) {
            return new RoleResponse(role);
        }

        throw new InternalException(ResponseCode.FAILED);
    }
}
