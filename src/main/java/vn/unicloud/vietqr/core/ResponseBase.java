package vn.unicloud.vietqr.core;

import lombok.Data;
import vn.unicloud.vietqr.enums.ResponseCode;

@Data
public class ResponseBase<T> {

    private int code;
    private String message;
    private T data;

    public ResponseBase(T data) {
        this.data = data;
        this.message = ResponseCode.SUCCESS.getMessage();
        this.code = ResponseCode.SUCCESS.getCode();
    }

    public ResponseBase(int code, String message) {
        this.message = message;
        this.code = code;
    }

}
