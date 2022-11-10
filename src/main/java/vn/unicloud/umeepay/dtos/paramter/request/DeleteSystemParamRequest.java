package vn.unicloud.umeepay.dtos.paramter.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class DeleteSystemParamRequest extends BaseRequestData {
    private Long id;
}
