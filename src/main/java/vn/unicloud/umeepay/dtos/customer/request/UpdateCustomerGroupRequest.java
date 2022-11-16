package vn.unicloud.umeepay.dtos.customer.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerGroupRequest extends BaseRequestData implements Serializable {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

}
