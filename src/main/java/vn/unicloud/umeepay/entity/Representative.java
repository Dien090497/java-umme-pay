package vn.unicloud.umeepay.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import vn.unicloud.umeepay.enums.Gender;
import vn.unicloud.umeepay.enums.IdentifyType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = Representative.COLLECTION_NAME)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Representative {

    public static final String COLLECTION_NAME = "representative";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @OneToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 6)
    private Gender gender;

    private String identifyNumber;

    @Enumerated(EnumType.STRING)
    private IdentifyType identifyType;

    private LocalDate issueDate;

    private String issueBy;

    private String nation;
}
