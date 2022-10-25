package vn.unicloud.umeepay.entity.merchant;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.entity.common.Permission;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = UserRole.COLLECTION_NAME)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserRole {

    public static final String COLLECTION_NAME = "user_role";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions;

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
