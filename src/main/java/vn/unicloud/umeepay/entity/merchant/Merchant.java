package vn.unicloud.umeepay.entity.merchant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import vn.unicloud.umeepay.entity.Auditable;
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

    @Column(name = "account_no", nullable = false)
    private String accountNo;

    private String webhookUrl;

    private String webhookApiKey;

    private LocalDateTime requestDate;

    private LocalDateTime approvedDate;

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
    private Representative representative;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "merchant")
    @JsonIgnore
    private BankAccount bankAccount;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "merchant")
    @JsonIgnore
    private List<Document> documents;

    @Override
    public String toString() {
        return "Merchant{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", accountNo='" + accountNo + '\'' +
                ", webhookUrl='" + webhookUrl + '\'' +
                ", webhookApiKey='" + webhookApiKey + '\'' +
                '}';
    }
}
