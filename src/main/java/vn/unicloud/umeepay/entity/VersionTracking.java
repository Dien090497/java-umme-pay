package vn.unicloud.umeepay.entity;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.enums.SystemModule;

import javax.persistence.*;

@Entity
@Table(name = VersionTracking.COLLECTION_NAME)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class VersionTracking {

    public static final String COLLECTION_NAME = "version_tracking";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "module_key")
    private SystemModule key;

    private Integer version;

}
