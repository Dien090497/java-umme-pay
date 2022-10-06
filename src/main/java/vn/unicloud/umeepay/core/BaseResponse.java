package vn.unicloud.umeepay.core;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BaseResponse {

    private int code;
    private String message;

}
