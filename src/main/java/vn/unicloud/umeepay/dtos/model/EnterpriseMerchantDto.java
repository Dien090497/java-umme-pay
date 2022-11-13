package vn.unicloud.umeepay.dtos.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseMerchantDto {

    @NotBlank
    private String fullName;

    @NotBlank
    private String idNo;

    @NotBlank
    private String position;

    @NotNull
    private Float fundPercent;

    @NotBlank
    private String representFrontUrl;

    @NotBlank
    private String representBackUrl;

    @NotBlank
    private String ownerFrontUrl;

    @NotBlank
    private String ownerbackUrl;

    private List<String> documentUrls;

}
