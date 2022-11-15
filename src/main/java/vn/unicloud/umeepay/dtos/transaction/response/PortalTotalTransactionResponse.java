package vn.unicloud.umeepay.dtos.transaction.response;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.core.BaseResponseData;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class PortalTotalTransactionResponse extends BaseResponseData {

    private Long total;

    private Long amount;

}
