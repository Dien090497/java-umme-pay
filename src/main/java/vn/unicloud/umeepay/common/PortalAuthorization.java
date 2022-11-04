package vn.unicloud.umeepay.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.entity.RoleGroup;
import vn.unicloud.umeepay.entity.User;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.service.RoleService;
import vn.unicloud.umeepay.service.UserService;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

import java.util.Arrays;

/**
 * Do authorization for MERCHANT APIS
 */
@Component
@RequiredArgsConstructor
public class PortalAuthorization implements IAuthorization {

    private final RedisService redisService;

    private final UserService userService;

    private final RoleService roleService;

    /**
     * @param loggedInId : logged in user's id
     * @param actions:    Action in the @Authorized annotation
     *                   ex: @Authorized(actions = {"SOME_ACTION"})
     * @return
     */
    @Override
    public boolean authorize(String loggedInId, String ...actions) {
        if (loggedInId == null || actions == null) {
            return false;
        }

        // Get current user - roleId mapping saved in redis, if not get from DB
        Long roleId = redisService.getValue(RedisKeyUtils.getUserRoleKey(loggedInId), Long.class);
        if (roleId == null) {
            User user = userService.getUserById(loggedInId);
            if (user != null && user.getRoleGroup() != null) {
                roleId = user.getRoleGroup().getId();
                redisService.setValue(RedisKeyUtils.getUserRoleKey(loggedInId), roleId);
            }
        }

        // Get actions of that role saved in redis, if not get from DB
        RoleGroup roleGr = redisService.getValue(RedisKeyUtils.getRoleKey(roleId), RoleGroup.class);
        if (roleGr == null && roleId != null) {
            roleGr = roleService.getRoleById(roleId);
            if (roleGr != null) {
                redisService.setValue(RedisKeyUtils.getRoleKey(roleId), roleGr);
            }
        }

        return roleGr != null &&
                roleGr.getActions() != null &&
                roleGr.getActions()
                        .stream()
                        .map(action -> action.getName())
                        .anyMatch(Arrays.asList(actions)::contains);
    }
}
