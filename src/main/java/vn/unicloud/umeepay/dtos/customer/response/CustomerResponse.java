package vn.unicloud.umeepay.dtos.customer.response;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.CustomerDto;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerResponse extends BaseResponseData {

    private CustomerDto customer;

}
