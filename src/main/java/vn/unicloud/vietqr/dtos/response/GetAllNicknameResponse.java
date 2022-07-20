package vn.unicloud.vietqr.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unicloud.vietqr.core.BaseResponseData;
import vn.unicloud.vietqr.dtos.model.NicknameResponse;
import vn.unicloud.vietqr.entity.Nickname;
import vn.unicloud.vietqr.utils.ModelMapperUtils;

import java.util.List;

@Data
@NoArgsConstructor
public class GetAllNicknameResponse extends BaseResponseData {

    private List<NicknameResponse> nicknames;

    public GetAllNicknameResponse(List<Nickname> nicknames) {
        this.nicknames = ModelMapperUtils.mapList(nicknames, NicknameResponse.class);
    }

}
