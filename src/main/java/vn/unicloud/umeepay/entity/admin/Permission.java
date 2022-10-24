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
@Table(name = Permission.COLLECTION_NAME)
public class Permission {

    public static final String COLLECTION_NAME = "permission";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SystemModule module;

    private String description;

}
