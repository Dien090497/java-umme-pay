package vn.unicloud.umeepay.dtos.user.response;

import lombok.*;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.User;
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

    public UserResponse(User user) {
        if (user == null) {
            return;
        }
        this.id = user.getId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.status = user.getStatus();
    }
}
