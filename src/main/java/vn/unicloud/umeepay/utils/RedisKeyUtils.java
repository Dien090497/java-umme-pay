package vn.unicloud.umeepay.utils;

public class RedisKeyUtils {

    private static final String UMPAY_REDIS_PREFIX_LOGIN_FAILED_DATA = "UMPAY_LOGIN_FAILED_DATA_";

    private static final String UMPAY_REDIS_PREFIX_USER_STATUS = "UMPAY_USER_STATUS_";

    private static final String UMPAY_REDIS_PREFIX_USER_ROLE = "UMPAY_USER_ROLE_";

    private static final String UMPAY_REDIS_PREFIX_ROLE_ACTIONS = "UMPAY_ROLE_ACTIONS_";

    public static String getLoginFailedData(String id) {
        return UMPAY_REDIS_PREFIX_LOGIN_FAILED_DATA + id;
    }

    public static String getUserStatusKey(String id) {
        return UMPAY_REDIS_PREFIX_USER_STATUS + id;
    }

    public static String getUserRoleKey(String id) {
        return UMPAY_REDIS_PREFIX_USER_ROLE + id;
    }

    public static String getRoleKey(Long roleId) {
        return UMPAY_REDIS_PREFIX_ROLE_ACTIONS + roleId;
    }

}
