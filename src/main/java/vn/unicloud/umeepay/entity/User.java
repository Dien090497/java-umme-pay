package vn.unicloud.umeepay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import vn.unicloud.umeepay.enums.UserStatus;

import javax.persistence.*;

@Entity
@Table(name = User.COLLECTION_NAME)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User extends Auditable<String> {

    public static final String COLLECTION_NAME = "user";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserStatus status = UserStatus.ACTIVE;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    @JsonIgnore
    private Merchant merchant;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_group_id")
    private RoleGroup roleGroup;

    private Boolean isOwner = false;  // Owner or members

    @Column(name = "subject_id", unique = true)
    private String subjectId;

    private boolean blockedCluster; // Bị khóa theo nhóm nếu owner bị inactive

    @Override
    public String toString() {
        return "User{" +
            "createdBy=" + createdBy +
            ", createdAt=" + createdAt +
            ", modifiedBy=" + modifiedBy +
            ", modifiedAt=" + modifiedAt +
            ", id='" + id + '\'' +
            ", status=" + status +
            ", username='" + username + '\'' +
            ", fullName='" + fullName + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", subjectId='" + subjectId + '\'' +
            '}';
    }
}
