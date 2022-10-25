package vn.unicloud.umeepay.entity.common;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.enums.SystemParameterGroup;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    private String currentValue;

    private String defaultValue;

    private String description;

    private SystemParameterGroup group;

}
