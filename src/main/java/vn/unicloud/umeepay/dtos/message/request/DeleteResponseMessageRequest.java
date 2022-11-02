package vn.unicloud.umeepay.dtos.message.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeleteResponseMessageRequest extends BaseRequestData {

    private Long id;

}
