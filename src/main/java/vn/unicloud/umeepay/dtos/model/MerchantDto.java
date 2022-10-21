package vn.unicloud.umeepay.dtos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import lombok.NoArgsConstructor;
import vn.unicloud.umeepay.entity.Credential;
import vn.unicloud.umeepay.entity.User;
import vn.unicloud.umeepay.enums.MerchantStatus;

import javax.persistence.*;
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
