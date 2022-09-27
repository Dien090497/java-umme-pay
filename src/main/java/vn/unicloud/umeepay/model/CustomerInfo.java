package vn.unicloud.umeepay.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class CustomerInfo {

    @NotEmpty
    private String fullName;

    @Email
    private String email;

    private String phone;

    private String address;

}
