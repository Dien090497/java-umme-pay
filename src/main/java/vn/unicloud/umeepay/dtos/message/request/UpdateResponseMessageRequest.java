package vn.unicloud.umeepay.dtos.message.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import vn.unicloud.umeepay.common.TrimString;
import vn.unicloud.umeepay.core.BaseRequestData;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpdateResponseMessageRequest extends BaseRequestData {

    @JsonIgnore
    private Long id;

    private Integer code;

    @JsonDeserialize(using = TrimString.class)
    private String viContent;

    @JsonDeserialize(using = TrimString.class)
    private String enContent;

    @JsonDeserialize(using = TrimString.class)
    private String description;
}
