package vn.unicloud.vietqr.dtos.request;

import lombok.Data;
import vn.unicloud.vietqr.core.BaseRequestData;
import vn.unicloud.vietqr.enums.Branch;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterNicknameRequest extends BaseRequestData {
    @NotBlank(message = "nickname không được để trống")
    private String nickname;

    private String cif;

    @NotBlank(message = "số tài khoản không được để trống")
    private String accountNo;

    private Branch branch;
}
