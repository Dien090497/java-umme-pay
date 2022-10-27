package vn.unicloud.umeepay.dtos.model;

import lombok.*;
import vn.unicloud.umeepay.entity.merchant.Credential;
import vn.unicloud.umeepay.enums.MerchantStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDto {

    private String id;

    private MerchantStatus status;

    private Credential credential;

    private LocalDateTime createDateTime;

    private String name;

    private String accountNo;

}
