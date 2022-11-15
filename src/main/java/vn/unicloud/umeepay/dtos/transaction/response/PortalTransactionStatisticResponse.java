package vn.unicloud.umeepay.dtos.transaction.response;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.enums.PaymentType;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class PortalTransactionStatisticResponse extends BaseResponseData {

    Map<LocalDate, Map<PaymentType, Long>> statistics;
    
}
