package vn.unicloud.umeepay.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.entity.Action;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.entity.RoleGroup;
import vn.unicloud.umeepay.enums.RoleType;
import vn.unicloud.umeepay.service.AdminService;
import vn.unicloud.umeepay.service.ContextService;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.service.RoleService;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Do authorization for ADMIN APIS
 */
@Component
@RequiredArgsConstructor
public class CmsAuthorization implements IAuthorization {

    private final RedisService redisService;

    private final AdminService adminService;

    private final RoleService roleService;

    private static final String FULL_PERMISSION_ROLE_GROUP_CODE = "CMS_FULL_PERMISSIONS";
    private final ContextService contextService;

    /**
     * @param loggedInId : logged in admin's id
     * @param actions:   Actions in the @Authorized annotation
     *                   ex: @Authorized(actions = {"SOME_ACTION"})
     * @return
     */
    @Override
    public boolean authorize(String loggedInId, String... actions) {
        if (loggedInId == null || actions == null) {
            return false;
        }

        // Allow all for root admin
        Set<String> loggedRole = contextService.getLoggedInUserRoles();
        if (loggedRole.contains(RoleType.ROOT_ADMIN.toString())) {
            return true;
        }

        // Get current user - roleId mapping saved in redis, if not get from DB
        Long roleId = redisService.getValue(RedisKeyUtils.getUserRoleKey(loggedInId), Long.class);
        if (roleId == null) {
            Administrator administrator = adminService.getById(loggedInId);
            if (administrator != null && administrator.getRoleGroup() != null) {
                roleId = administrator.getRoleGroup().getId();
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

        if (roleGr != null && FULL_PERMISSION_ROLE_GROUP_CODE.equals(roleGr.getCode())) {
            return true;
        }

        return roleGr != null &&
                roleGr.getActions() != null &&
                roleGr.getActions()
                        .stream()
                        .map(Action::getName)
                        .anyMatch(Arrays.asList(actions)::contains);
    }
}
