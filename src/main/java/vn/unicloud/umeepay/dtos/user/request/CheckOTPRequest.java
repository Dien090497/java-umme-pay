package vn.unicloud.umeepay.dtos.user.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CheckOTPRequest extends BaseRequestData {

    @NotBlank
    private String sessionId;

    @NotBlank
    @Size(min = 6, max = 6)
    private String otp;

    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
    private String phone;

}
