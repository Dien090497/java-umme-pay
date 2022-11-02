package vn.unicloud.umeepay.enums;

public enum ResponseCode {
    // Common
    SUCCESS(0, "Success"),
    FAILED(1, "Failed"),
    COMMON_ERROR(2, "Common Error"),
    INVALID_PARAM(3, "Invalid param"),
    INVALID_SESSION(4, "Invalid session"),
    UNHANDLE_REQUEST(5, "Unhandle request"),
    INVALID_REFRESH_TOKEN(6, "Invalid refresh token"),
    ACCESS_DENIED(7, "Access denied"),

    // User
    EXISTED_EMAIL(100, "Existed email"),
    EXISTED_PHONE(101, "Existed phone"),
    OTP_WAS_EXPIRED(102, "OTP was expired"),
    ACCOUNT_DID_NOT_LINK(103, "Account did not link"),
    USER_NOT_FOUND(104, "User not found"),
    INVALID_USERNAME_OR_PASSWORD(105, "Invalid username or password"),
    CREATE_USER_FAILED(106, "Create user failed"),
    EXISTED_OTP(107, "Existed OTP"),
    OTP_INVALID(108, "Invalid OTP"),
    PHONE_NUMBER_INVALID(109, "Phone number invalid"),
    PASSWORD_CHANGE_FAILED(110, "Change password failed"),
    EXISTED_USERNAME(111, "Existed username"),
    INACTIVE_ACCOUNT(112, "Inactive account"),
    BLOCKED_ACCOUNT(112, "Blocked account"),
    LOGGED_IN_ACCOUNT(113, "Account was logged into the system"),
    INVALID_USER_STATUS(114, "Account status is invalid"),

    // Nickname
    EXISTED_NICKNAME(201, "Existed Nickname"),

    // Client
    CREATE_CLIENT_FAIL(301, "Create client failed"),

    CLIENT_LOGIN_FAILED(302, "Client login failed"),

    TRANSACTION_NOT_FOUND(303, "Transaction not found"),

    INVALID_KEY_ID(304, "Invalid key id"),

    AUTHORIZATION_FAILED(306, "Authorization failed"),

    // Merchant

    TRANSACTION_TIMEOUT(401, "Scan QR code timeout"),

    INVALID_TRANSACTION_ID(402, "Invalid transactionId"),

    INVALID_VIRTUAL_ACCOUNT(403, "Invalid virtual account"),

    INVALID_AMOUNT(404, "Invalid amount"),

    INVALID_TRANSACTION_STATE(405, "Invalid transaction state"),

    TRANSACTION_CANCELED(406, "Canceled transaction"),

    TRANSACTION_FAILED(407, "Transaction failed"),

    MERCHANT_ALREADY_CREATED(408, "Merchant already created"),

    MERCHANT_NOT_FOUND(409, "Merchant not found"),

    INVALID_DATA(410, "Invalid data"),

    DUPLICATE_REFERENCE_TRANSACTION_ID(411, "Duplicate ref transaction id"),

    CALL_WEBHOOK_ERROR(412, "Call webhook error"),

    TRANSACTION_EXPIRED(413, "Transaction expired"),

    // Paygate
    INVALID_CERTIFICATE(501, "Invalid certificate"),

    // Json
    CANT_MAPPING_JSON_OBJECT(601, "Can't mapping json object"),


    // Test API
    HTTP_STATUS_FAILED(701, "Http Status failed"),

    RESPONSE_BODY_NULL(702, "Response body null"),


    // Role
    ROLE_ERROR_EXISTED_CODE(801, "Role with request code existed"),

    ROLE_ERROR_ACTION_NOT_FOUND(802, "Role action not found"),

    ROLE_ERROR_NOT_FOUND(803, "Role not found"),

    ROLE_ERROR_NOT_EMPTY(804, "There is/are user(s) in this role"),

    // Auth
    AUTH_ERROR_INCORRECT_PASSWORD(901, "Incorrect password"),


    // Response message
    RESPONSE_MESSAGE_ERROR_EXISTED_CODE(1001, "Existed response code"),

    RESPONSE_MESSAGE_ERROR_NOT_FOUND(1002, "Response message not found"),

    // System parameter
    SYSTEM_PARAMETER_ERROR_NOT_FOUND(1101, "System parameter not found"),
    SYSTEM_PARAMETER_ERROR_INVALID_VALUE(1102, "Invalid value"),
    SYSTEM_PARAMETER_ERROR_EXISTED_NAME(1102, "System parameter with request name existed");


    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
