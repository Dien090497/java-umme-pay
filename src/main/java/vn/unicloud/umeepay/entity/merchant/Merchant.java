package vn.unicloud.umeepay.entity.merchant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import vn.unicloud.umeepay.entity.admin.Administrator;
import vn.unicloud.umeepay.entity.common.Auditable;
import vn.unicloud.umeepay.enums.MerchantStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = Merchant.COLLECTION_NAME)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Merchant extends Auditable<String> {

    public static final String COLLECTION_NAME = "merchant";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Enumerated(EnumType.STRING)
    private MerchantStatus status;

    private String accountNo;

    private String webhookUrl;

    private String webhookApiKey;

    private LocalDateTime requestAt;

    private LocalDateTime approvedAt;

    private String name;

    @ManyToOne
    @JoinColumn(name = "approved_by_id", referencedColumnName = "id")
    private Administrator approvedBy;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "merchant"
    )
    @JsonIgnore
    private List<User> users;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "merchant")
    @JsonIgnore
    private Credential credential;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "merchant")
    @JsonIgnore
    private Profile profile;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "merchant")
    @JsonIgnore
    private BankAccount bankAccount;

    @Override
    public String toString() {
        return "Merchant{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", accountId='" + id + '\'' +
                ", webhookUrl='" + webhookUrl + '\'' +
                ", webhookApiKey='" + webhookApiKey + '\'' +
                '}';
    }
}
