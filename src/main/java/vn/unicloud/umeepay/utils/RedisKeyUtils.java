package vn.unicloud.umeepay.utils;

public class RedisKeyUtils {

    public static String getLoginFailedData(String id) {
        return "login_failed_data_" + id;
    }
    
}
