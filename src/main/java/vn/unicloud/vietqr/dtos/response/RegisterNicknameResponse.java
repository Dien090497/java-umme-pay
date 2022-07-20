package vn.unicloud.vietqr.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unicloud.vietqr.core.BaseResponseData;
import vn.unicloud.vietqr.dtos.model.NicknameResponse;
import vn.unicloud.vietqr.entity.Nickname;
import vn.unicloud.vietqr.utils.ModelMapperUtils;

@Data
@NoArgsConstructor
public class RegisterNicknameResponse extends BaseResponseData {

    private boolean status;

    private NicknameResponse nickname;

    public RegisterNicknameResponse(boolean status, Nickname nickname) {
        this.status = status;
        this.nickname = ModelMapperUtils.mapper(nickname, NicknameResponse.class);
    }

}
