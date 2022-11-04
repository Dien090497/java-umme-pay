package vn.unicloud.umeepay.utils;

public class RedisKeyUtils {

    private static final String UMPAY_REDIS_PREFIX_LOGIN_FAILED_DATA = "UMPAY_LOGIN_FAILED_DATA_";

    private static final String UMPAY_REDIS_PREFIX_USER_STATUS = "UMPAY_USER_STATUS_";

    private static final String OTP_KEY = "UPAY_OTP_KEY_";

    private static final String CHANGE_PASSWORD_SESSION = "UPAY_CHANGE_PASSWORD_SESSION_";

    private static final String UMPAY_REDIS_PREFIX_USER_ROLE = "UPAY_USER_ROLE_";

    private static final String UMPAY_REDIS_PREFIX_ROLE = "UPAY_ROLE_";

    private static final String UMPAY_REDIS_PREFIX_USER_SUBJECT = "UPAY_USER_SUBJECT_";

    public static String getLoginFailedData(String id) {
        return UMPAY_REDIS_PREFIX_LOGIN_FAILED_DATA + id;
    }

    public static String getUserStatusKey(String id) {
        return UMPAY_REDIS_PREFIX_USER_STATUS + id;
    }

    public static String getOtpKey(String phone) {
        return OTP_KEY + phone;
    }

    public static String getChangePasswordSession(String id) {
        return CHANGE_PASSWORD_SESSION + id;
    }

    public static String getUserRoleKey(String userId) {
        return UMPAY_REDIS_PREFIX_USER_ROLE + userId;
    }

    public static String getRoleKey(Long roleId) {
        return UMPAY_REDIS_PREFIX_ROLE + roleId;
    }

    public static String getUserSubjectKey(String subjectId) {
        return UMPAY_REDIS_PREFIX_USER_SUBJECT + subjectId;
    }
}
