package vn.unicloud.umeepay.dtos.paramter.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import vn.unicloud.umeepay.common.TrimString;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.enums.SystemParameterGroup;
import vn.unicloud.umeepay.enums.SystemParameterType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateSystemParamRequest extends BaseRequestData {

    @NotEmpty
    @JsonDeserialize(using = TrimString.class)
    @Size(max = 50)
    private String name;

    @NotNull
    private SystemParameterGroup group;

    @NotNull
    private SystemParameterType dataType;

    @NotEmpty
    @JsonDeserialize(using = TrimString.class)
    @Size(max = 100)
    private String value;

    @Size(max = 250)
    @JsonDeserialize(using = TrimString.class)
    private String description;

}
