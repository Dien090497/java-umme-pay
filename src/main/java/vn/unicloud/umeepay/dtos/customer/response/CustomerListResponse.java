package vn.unicloud.umeepay.dtos.customer.response;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.CustomerDto;

import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerListResponse extends BaseResponseData {

    private List<CustomerDto> customers;

}
