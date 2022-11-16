package vn.unicloud.umeepay.dtos.customer.response;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.dtos.model.CustomerGroupDto;

import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerGroupListResponse extends BaseResponseData {

    private List<CustomerGroupDto> groups;

}
