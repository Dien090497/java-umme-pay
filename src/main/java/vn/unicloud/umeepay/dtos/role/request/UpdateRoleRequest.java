package vn.unicloud.umeepay.dtos.role.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import vn.unicloud.umeepay.common.TrimString;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.enums.RoleStatus;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleRequest extends BaseRequestData {

    @JsonIgnore
    private Long id;

    @Size(max = 100)
    @JsonDeserialize(using = TrimString.class)
    private String name;

    @Size(max = 200)
    @JsonDeserialize(using = TrimString.class)
    private String description;

    private RoleStatus status;

    @UniqueElements
    private List<Long> actionIds;

}
