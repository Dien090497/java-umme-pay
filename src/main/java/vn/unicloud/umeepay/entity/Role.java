package vn.unicloud.umeepay.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.enums.RoleStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Role.COLLECTION_NAME)
public class Role extends Auditable<String> {

    public static final String COLLECTION_NAME = "role";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private String description;

    @Enumerated(EnumType.STRING)
    private RoleStatus status;

    @ManyToMany
    private List<Permission> permissions;

}
