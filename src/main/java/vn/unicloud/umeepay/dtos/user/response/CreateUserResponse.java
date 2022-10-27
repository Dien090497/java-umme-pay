package vn.unicloud.umeepay.dtos.user.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class CreateUserResponse extends BaseResponseData {
    private boolean success;
}
