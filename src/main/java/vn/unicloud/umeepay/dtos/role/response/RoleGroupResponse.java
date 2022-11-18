 package vn.unicloud.umeepay.dtos.role.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.RoleGroup;
import vn.unicloud.umeepay.enums.RoleStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleGroupResponse extends BaseResponseData {
    private Long id;
    private String name;
    private String code;
    private String description;
    private RoleStatus status;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public RoleGroupResponse(RoleGroup role) {
        if (role == null) {
            return;
        }
        this.id = role.getId();
        this.name = role.getName();
        this.code = role.getCode();
        this.description = role.getDescription();
        this.status = role.getStatus();
        this.createdAt = role.getCreatedAt();
        this.modifiedAt = role.getModifiedAt();
        this.createdBy = role.getCreatedBy();
        this.modifiedBy = role.getModifiedBy();
    }

}
