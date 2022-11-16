package vn.unicloud.umeepay.dtos.paylink.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class GetPayLinkRequest extends BaseRequestData {

    private String payLinkCode;

}
