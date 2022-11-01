package vn.unicloud.umeepay.dtos.message.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListResponseMessageResponse extends BaseResponseData {

    private List<ResponseMessageResponse> messages;

}
