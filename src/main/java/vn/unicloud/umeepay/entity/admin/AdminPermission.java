package vn.unicloud.umeepay.entity.admin;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.enums.SystemModule;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = AdminPermission.COLLECTION_NAME)
public class AdminPermission {

    public static final String COLLECTION_NAME = "admin_permission";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SystemModule module;

    private String description;

    @Override
    public String toString() {
        return "AdminPermission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", module=" + module +
                ", description='" + description + '\'' +
                '}';
    }
}
