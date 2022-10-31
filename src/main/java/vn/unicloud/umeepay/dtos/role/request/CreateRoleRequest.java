package vn.unicloud.umeepay.dtos.role.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import vn.unicloud.umeepay.common.TrimString;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.enums.RoleStatus;
import vn.unicloud.umeepay.enums.RoleType;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleRequest extends BaseRequestData {

    @NotBlank
    @Size(max = 20)
    @JsonDeserialize(using = TrimString.class)
    @Pattern(regexp = "^\\w+$", message = "Invalid code")
    private String code;

    @NotBlank
    @Size(max = 100)
    @JsonDeserialize(using = TrimString.class)
    private String name;

    @Size(max = 200)
    @JsonDeserialize(using = TrimString.class)
    private String description;

    @NotNull
    private RoleStatus status;

    @UniqueElements
    private List<Long> actionIds;

    @JsonIgnore
    private RoleType scope; // Admin role or merchant role

}
