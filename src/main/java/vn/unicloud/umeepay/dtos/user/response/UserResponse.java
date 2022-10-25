package vn.unicloud.umeepay.dtos.user.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.merchant.User;
import vn.unicloud.umeepay.enums.UserStatus;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends BaseResponseData {
    private String id;
    private String username;
    private String fullName;
    private String email;
    private UserStatus status;
    private String phone;
    private UserRoleResponse role;

    public UserResponse(User user) {
        if(user == null) {
            return;
        }
        this.id = user.getId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.status = user.getStatus();
        this.role = new UserRoleResponse(user.getRole());
    }
}
