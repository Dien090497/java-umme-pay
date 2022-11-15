package vn.unicloud.umeepay.dtos.transaction.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PortalTotalTransactionRequest extends BaseRequestData {

    private LocalDate date;

}
