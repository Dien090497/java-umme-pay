package vn.unicloud.umeepay.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class CustomerInfo {

    @NotEmpty
    private String fullName;

    @Email
    private String email;

    private String phone;

    private String address;

}
