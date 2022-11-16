package vn.unicloud.umeepay.dtos.customer.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest extends BaseRequestData implements Serializable {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String address;

    private String groupId;

}
