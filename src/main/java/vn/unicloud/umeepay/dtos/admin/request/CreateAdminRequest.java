package vn.unicloud.umeepay.dtos.admin.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.common.TrimString;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.enums.OfficeType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    private String username;

    @NotBlank
    @Email
    @JsonDeserialize(using = TrimString.class)
    private String email;

    @NotBlank
    @JsonDeserialize(using = TrimString.class)
    private String fullName;

    @NotBlank
    @JsonDeserialize(using = TrimString.class)
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid phone number")
    private String phone;

    @NotBlank
    @JsonDeserialize(using = TrimString.class)
    private String staffId;

    @NotNull
    private OfficeType office;

    @JsonDeserialize(using = TrimString.class)
    private String description;

    @NotNull
    private Long roleId;

}
