package vn.unicloud.umeepay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.enums.RoleStatus;
import vn.unicloud.umeepay.enums.RoleType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = RoleGroup.COLLECTION_NAME)
public class RoleGroup extends Auditable<String> {

    public static final String COLLECTION_NAME = "role_group";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String code;

    private String description;

    @Enumerated(EnumType.STRING)
    private RoleStatus status = RoleStatus.ACTIVE;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_group_actions",
            joinColumns = @JoinColumn(name = "role_group_id"),
            inverseJoinColumns = @JoinColumn(name = "action_id")
    )
    private List<Action> actions;

    @Enumerated(EnumType.STRING)
    private RoleType scope;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "roleGroup"
    )
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "roleGroup"
    )
    @JsonIgnore
    private List<Administrator> admins = new ArrayList<>();

    @Override
    public String toString() {
        return "RoleGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
