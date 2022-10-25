package vn.unicloud.umeepay.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import vn.unicloud.umeepay.entity.common.Auditable;
import vn.unicloud.umeepay.enums.OfficeType;
import vn.unicloud.umeepay.enums.UserStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Administrator.COLLECTION_NAME)
public class Administrator extends Auditable<String> {

    public static final String COLLECTION_NAME = "admin";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private String username;

    private String staffId;

    private String email;

    private String phone;

    private String name;

    @Enumerated(EnumType.STRING)
    private OfficeType office;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_role_id")
    private AdminRole roles;

    private LocalDateTime blockedAt;

    private String blockedBy;

    @Override
    public String toString() {
        return "Administrator{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", staffId='" + staffId + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", office=" + office +
                ", status=" + status +
                '}';
    }
}
