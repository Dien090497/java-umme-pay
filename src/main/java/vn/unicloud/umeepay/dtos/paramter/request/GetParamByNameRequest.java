package vn.unicloud.umeepay.dtos.paramter.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetParamByNameRequest extends BaseRequestData {

    private String name;

}
