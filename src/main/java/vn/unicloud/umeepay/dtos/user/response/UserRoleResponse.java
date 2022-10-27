package vn.unicloud.umeepay.dtos.user.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.merchant.UserRole;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleResponse extends BaseResponseData {
    private Long id;
    private String name;
    private String description;

    public UserRoleResponse(UserRole userRole) {
        if (userRole == null) {
            return;
        }
        this.id = userRole.getId();
        this.name = userRole.getName();
        this.description = userRole.getDescription();
    }
}
