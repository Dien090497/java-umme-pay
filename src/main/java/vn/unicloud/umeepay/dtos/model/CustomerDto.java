package vn.unicloud.umeepay.dtos.model;

import lombok.*;
import vn.unicloud.umeepay.entity.Customer;
import vn.unicloud.umeepay.entity.CustomerGroup;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String id;

    private String fullName;

    private String email;

    private String phone;

    private String address;

    private String customerGroupId;

    private String customerGroupName;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.fullName = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.address = customer.getAddress();
        CustomerGroup groupCustomer = customer.getGroupCustomer();
        if (groupCustomer != null) {
            this.customerGroupId = groupCustomer.getId();
            this.customerGroupName = groupCustomer.getName();
        }
    }

}
