package vn.unicloud.umeepay.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.enums.Branch;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class RegisterNicknameRequest extends BaseRequestData {
    @NotBlank(message = "nickname không được để trống")
    private String nickname;

    private String cif;

    @NotBlank(message = "số tài khoản không được để trống")
    private String accountNo;

    private Branch branch;
}
