package vn.unicloud.vietqr.enums;

public enum ResponseCode {
    // Common
    SUCCESS(0,"Success"),
    FAILED(1,"Failed"),
    COMMON_ERROR(2, "Common Error"),
    INVALID_PARAM(3, "Invalid param"),
    INVALID_SESSION(4, "Invalid session"),
    UNHANDLE_REQUEST(5, "Unhandle request"),

    // User
    EXISTED_EMAIL(100, "Existed email"),
    EXISTED_PHONE(101, "Existed phone"),
    OTP_WAS_EXPIRED(102, "OTP was expired"),
    ACCOUNT_DID_NOT_LINK(103, "Account did not link"),
    USER_NOT_FOUND(104, "User not found"),

    // Nickname
    EXISTED_NICKNAME(201, "Existed Nickname"),

    // Client
    CREATE_CLIENT_FAIL(301, "Create client failed"),
    CLIENT_LOGIN_FAILED(302, "Client login failed"),

    // VietQR
    VIETQR_TIMEOUT(401, "Scan QR code timeout"),
    INVALID_TRANSACTION_ID(402, "Invalid transactionId"),
    INVALID_VIRTUAL_ACCOUNT(403, "Invalid virtual account"),
    INVALID_AMOUNT(404, "Invalid amount"),
    INVALID_TRANSACTION_STATE(405, "Invalid transaction state"),
    TRANSACTION_CANCELED(406, "Canceled transaction"),
    TRANSACTION_FAILED(407, "Transaction failed"),

    // Paygate
    INVALID_CERTIFICATE(501, "Invalid certificate"),
    ;

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
