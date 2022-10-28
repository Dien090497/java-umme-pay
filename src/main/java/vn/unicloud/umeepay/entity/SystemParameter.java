package vn.unicloud.umeepay.entity;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.enums.SystemParameterGroup;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = SystemParameter.COLLECTION_NAME)
public class SystemParameter extends Auditable<String>{

    public static final String COLLECTION_NAME = "system_parameter";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String value;

    private String description;

    @Column(name = "param_group")
    private SystemParameterGroup group;

}
