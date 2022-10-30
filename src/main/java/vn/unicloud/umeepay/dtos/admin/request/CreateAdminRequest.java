package vn.unicloud.umeepay.dtos.admin.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.common.TrimString;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.enums.OfficeType;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdminRequest extends BaseRequestData {

    @NotBlank
    @JsonDeserialize(using = TrimString.class)
    @Pattern(regexp = "^\\w+$", message = "Invalid username")
    @Size(max = 20)
    private String username;

    @NotBlank
    @Email
    @JsonDeserialize(using = TrimString.class)
    @Size(max = 100)
    private String email;

    @NotBlank
    @JsonDeserialize(using = TrimString.class)
    @Size(max = 100)
    private String fullName;

    @JsonDeserialize(using = TrimString.class)
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid phone number")
    private String phone;

    @JsonDeserialize(using = TrimString.class)
    @Size(max = 15)
    private String staffId;

    @NotNull
    private OfficeType office;

    @JsonDeserialize(using = TrimString.class)
    @Size(max = 250)
    private String description;

    private Long roleId;

}
