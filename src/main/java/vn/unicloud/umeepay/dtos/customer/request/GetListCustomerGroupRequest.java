package vn.unicloud.umeepay.dtos.customer.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.unicloud.umeepay.core.BaseRequestData;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetListCustomerGroupRequest extends BaseRequestData implements Serializable {

}
