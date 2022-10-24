package vn.unicloud.umeepay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import vn.unicloud.umeepay.enums.UserRole;
import vn.unicloud.umeepay.enums.UserStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = User.COLLECTION_NAME)
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private UserStatus status;

    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    @JsonIgnore
    private Merchant merchant;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String blockedBy;

    private LocalDateTime blockedAt;
}
