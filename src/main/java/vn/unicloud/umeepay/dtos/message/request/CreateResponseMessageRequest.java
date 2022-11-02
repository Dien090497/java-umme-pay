package vn.unicloud.umeepay.dtos.message.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.common.TrimString;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CreateResponseMessageRequest extends BaseRequestData {

    @NotNull
    @PositiveOrZero
    private Integer code;

    @NotBlank
    @JsonDeserialize(using = TrimString.class)
    private String viContent;

    @NotBlank
    @JsonDeserialize(using = TrimString.class)
    private String enContent;

    @JsonDeserialize(using = TrimString.class)
    private String description;

}
