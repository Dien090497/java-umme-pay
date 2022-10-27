package vn.unicloud.umeepay.entity;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.enums.RoleType;
import vn.unicloud.umeepay.enums.SystemModule;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Permission.COLLECTION_NAME)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Permission {

    public static final String COLLECTION_NAME = "permission";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private RoleType scope;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "permission_actions",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "action_id")
    )
    private List<Action> actions;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", scope=" + scope +
                '}';
    }
}
