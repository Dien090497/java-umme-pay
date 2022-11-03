package vn.unicloud.umeepay.dtos.message.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GetResMessageByCodeRequest extends BaseRequestData {

    @NotNull
    private Integer code;

}
