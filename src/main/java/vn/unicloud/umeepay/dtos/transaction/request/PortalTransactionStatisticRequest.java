package vn.unicloud.umeepay.dtos.transaction.request;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.core.BaseRequestData;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PortalTransactionStatisticRequest extends BaseRequestData {

    private LocalDate from;

    private LocalDate to;

}
