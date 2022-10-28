package vn.unicloud.umeepay.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import vn.unicloud.umeepay.enums.Office;
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
    @Column(unique = true, nullable = false)
    private String id;

    private String username;

    private String staffId;

    private String email;

    private String phone;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private Office office;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_role_id")
    private Role role;

    private LocalDateTime blockedAt;

    private String blockedBy;

    private Boolean loggedIn = false;

    @Override
    public String toString() {
        return "Administrator{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", staffId='" + staffId + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", fullName='" + fullName + '\'' +
                ", office=" + office +
                ", status=" + status +
                '}';
    }
}
