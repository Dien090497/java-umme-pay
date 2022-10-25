package vn.unicloud.umeepay.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.entity.Auditable;
import vn.unicloud.umeepay.enums.RoleStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = AdminRole.COLLECTION_NAME)
public class AdminRole extends Auditable<String> {

    public static final String COLLECTION_NAME = "admin_role";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private String description;

    @Enumerated(EnumType.STRING)
    private RoleStatus status;

    @ManyToMany
    @JoinTable(
            name = "admin_role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<AdminPermission> permissions;

    @Override
    public String toString() {
        return "AdminRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
