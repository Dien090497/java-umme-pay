package vn.unicloud.umeepay.dtos.payment.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import lombok.NoArgsConstructor;
import vn.unicloud.umeepay.core.BaseResponseData;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CancelTransactionResponse extends BaseResponseData {

    private boolean success;

}
