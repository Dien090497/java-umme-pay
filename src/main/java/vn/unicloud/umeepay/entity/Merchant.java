package vn.unicloud.umeepay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    private String accountNo;

    private String webhookUrl;

    private String webhookApiKey;


    private LocalDateTime requestAt;

    private LocalDateTime approvedAt;

    private String name;

    @ManyToOne
    @JoinColumn(name = "approved_by_id", referencedColumnName = "id")
    private Administrator approvedBy;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "merchant_members",
            joinColumns = @JoinColumn(name = "merchant_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    @JsonIgnore
    private List<User> members;

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
