package vn.unicloud.umeepay.dtos.paramter.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.SystemParameter;
import vn.unicloud.umeepay.enums.SystemParameterGroup;
import vn.unicloud.umeepay.enums.SystemParameterType;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SystemParamResponse extends BaseResponseData {
    private Long id;

    private String name;

    private String value;

    private String description;

    private SystemParameterType dataType;

    private SystemParameterGroup group;

    private LocalDateTime createdAt;

    private String createBy;

    private LocalDateTime modifiedAt;

    private String modifiedBy;

    public SystemParamResponse(SystemParameter parameter) {
        if (parameter == null) {
            return;
        }

        this.id = parameter.getId();
        this.name = parameter.getName();
        this.value = parameter.getValue();
        this.description = parameter.getDescription();
        this.dataType = parameter.getDataType();
        this.group = parameter.getGroup();
        this.createBy = parameter.getCreatedBy();
        this.createdAt = parameter.getCreatedAt();
        this.modifiedAt = parameter.getModifiedAt();
        this.modifiedBy = parameter.getModifiedBy();
    }
}
