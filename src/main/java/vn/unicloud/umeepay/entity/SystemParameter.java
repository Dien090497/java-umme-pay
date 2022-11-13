package vn.unicloud.umeepay.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.envers.Audited;
import vn.unicloud.umeepay.enums.SystemParameterGroup;
import vn.unicloud.umeepay.enums.SystemParameterType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = SystemParameter.COLLECTION_NAME)
@Audited
public class SystemParameter extends Auditable<String> {

    public static final String COLLECTION_NAME = "system_parameter";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String value;

    private String description;

    @Column(name = "data_type")
    @Enumerated(EnumType.STRING)
    private SystemParameterType dataType;

    @Column(name = "param_group")
    @Enumerated(EnumType.STRING)
    private SystemParameterGroup group;

}
