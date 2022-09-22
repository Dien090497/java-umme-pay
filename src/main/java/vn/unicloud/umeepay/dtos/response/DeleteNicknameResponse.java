package vn.unicloud.umeepay.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.umeepay.core.BaseResponseData;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class DeleteNicknameResponse extends BaseResponseData {

    @NotEmpty
    private boolean status;

}
