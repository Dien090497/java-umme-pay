package vn.unicloud.umeepay.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseResponseData;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CheckNicknameResponse extends BaseResponseData {

    @NotEmpty
    private boolean isExisted;

}
