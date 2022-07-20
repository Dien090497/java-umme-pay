package vn.unicloud.vietqr.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unicloud.vietqr.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteNicknameRequest extends BaseRequestData {
    @NotEmpty
    private String id;
}
