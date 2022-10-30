package vn.unicloud.umeepay.utils;

public class RedisKeyUtils {

    private static final String UMPAY_REDIS_PREFIX_LOGIN_FAILED_DATA = "UMPAY_LOGIN_FAILED_DATA_";

    private static final String UMPAY_REDIS_PREFIX_USER_STATUS = "UMPAY_USER_STATUS_";

    public static String getLoginFailedData(String id) {
        return UMPAY_REDIS_PREFIX_LOGIN_FAILED_DATA + id;
    }

    public static String getUserStatusKey(String id) {
        return UMPAY_REDIS_PREFIX_USER_STATUS + id;
    }

}
