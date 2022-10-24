package vn.unicloud.umeepay.entity.merchant;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = UserRole.COLLECTION_NAME)
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    public static final String COLLECTION_NAME = "user_role";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "user_role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<UserPermission> permissions;

}
