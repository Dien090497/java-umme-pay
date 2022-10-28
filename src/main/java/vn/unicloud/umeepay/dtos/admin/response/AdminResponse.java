package vn.unicloud.umeepay.dtos.admin.response;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.enums.UserStatus;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminResponse extends BaseResponseData {
    private String id;
    private String username;
    private String name;
    private String email;
    private UserStatus status;
    private String phone;

    private Boolean loggedIn;

    public AdminResponse(Administrator admin) {
        if (admin == null) {
            return;
        }
        this.id = admin.getId();
        this.username = admin.getUsername();
        this.name = admin.getFullName();
        this.email = admin.getEmail();
        this.phone = admin.getPhone();
        this.status = admin.getStatus();
        this.loggedIn = admin.getLoggedIn();
    }
}
