package vn.unicloud.umeepay.dtos.paramter.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AllSystemParamResponse extends BaseResponseData {

    private List<SystemParamResponse> parameters;

}
