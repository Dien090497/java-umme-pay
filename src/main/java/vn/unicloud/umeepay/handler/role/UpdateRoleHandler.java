package vn.unicloud.umeepay.handler.role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.role.request.UpdateRoleRequest;
import vn.unicloud.umeepay.dtos.role.response.RoleResponse;
import vn.unicloud.umeepay.entity.Action;
import vn.unicloud.umeepay.entity.Role;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.ActionService;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.service.RoleService;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateRoleHandler extends RequestHandler<UpdateRoleRequest, RoleResponse> {

    private final RoleService roleService;

    private final ActionService actionService;

    private final RedisService redisService;

    @Override
    public RoleResponse handle(UpdateRoleRequest request) {
        Role role = roleService.getRoleById(request.getId());
        if (role == null) {
            throw new InternalException(ResponseCode.ROLE_ERROR_NOT_FOUND);
        }

        role
                .setName(request.getName() != null
                        ? request.getName()
                        : role.getName())

                .setDescription(request.getDescription() != null
                        ? request.getDescription()
                        : role.getDescription())

                .setStatus(request.getStatus() != null
                        ? request.getStatus()
                        : role.getStatus());

        if (request.getActionIds() != null) {
            List<Action> actions = request.getActionIds()
                    .stream()
                    .map(id -> {
                        Action action = actionService.getById(id);
                        if (action == null) {
                            throw new InternalException(ResponseCode.ROLE_ERROR_ACTION_NOT_FOUND);
                        }
                        return action;
                    })
                    .collect(Collectors.toList());

            role.setActions(actions);

            // Update redis
            redisService.setValue(RedisKeyUtils.getRoleKey(role.getId()), role);

        }

        if (roleService.saveRole(role) != null) {
            return new RoleResponse(role);
        }

        throw new InternalException(ResponseCode.FAILED);
    }
}
