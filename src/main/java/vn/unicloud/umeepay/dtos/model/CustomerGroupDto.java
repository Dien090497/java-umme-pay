package vn.unicloud.umeepay.dtos.model;

import lombok.*;
import vn.unicloud.umeepay.entity.CustomerGroup;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerGroupDto {

    private String id;

    private String name;

    public CustomerGroupDto(CustomerGroup customerGroup) {
        this.id = customerGroup.getId();
        this.name = customerGroup.getName();
    }

}
