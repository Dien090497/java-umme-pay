package vn.unicloud.vietqr.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.vietqr.core.BaseResponseData;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class CheckNicknameResponse extends BaseResponseData {

    @NotEmpty
    private boolean isExisted;

}
