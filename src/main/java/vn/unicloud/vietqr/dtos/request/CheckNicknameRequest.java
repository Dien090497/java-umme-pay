package vn.unicloud.vietqr.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.unicloud.vietqr.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class CheckNicknameRequest extends BaseRequestData {
    @NotEmpty
    private String nickname;
}
