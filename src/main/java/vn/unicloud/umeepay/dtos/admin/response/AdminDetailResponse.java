package vn.unicloud.umeepay.dtos.admin.response;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.entity.Administrator;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AdminDetailResponse extends AdminResponse {
    private LocalDateTime createAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private LocalDateTime blockedAt;
    private String blockedBy;

    public AdminDetailResponse(Administrator admin) {
        super(admin);

        if (admin == null) {
            return;
        }
        this.createAt = admin.getCreatedAt();
        this.createdBy = admin.getCreatedBy();
        this.modifiedAt = admin.getModifiedAt();
        this.modifiedBy = admin.getModifiedBy();
        this.blockedAt = admin.getBlockedAt();
        this.blockedBy = admin.getBlockedBy();
    }
}
