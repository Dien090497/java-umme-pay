package vn.unicloud.umeepay.dtos.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoForm {

    private String id;

    private boolean fillName;

    private boolean fillPhone;

    private boolean fillEmail;

    private boolean fillAddress;

}
