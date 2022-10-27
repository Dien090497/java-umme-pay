package vn.unicloud.umeepay.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.enums.ResponseCode;

@Getter
@Setter
@ToString
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
