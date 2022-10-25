package vn.unicloud.umeepay.entity.merchant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import vn.unicloud.umeepay.entity.common.Auditable;
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
    @Column(unique = true, nullable = false)
    private String id;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    @JsonIgnore
    private Merchant merchant;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_role_id")
    private UserRole role;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
