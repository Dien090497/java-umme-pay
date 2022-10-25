package vn.unicloud.umeepay.entity.common;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.enums.SystemModule;
import vn.unicloud.umeepay.enums.RoleType;

import javax.persistence.*;

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

    private SystemModule module;

    private RoleType type;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", module=" + module +
                ", type=" + type +
                '}';
    }
}
