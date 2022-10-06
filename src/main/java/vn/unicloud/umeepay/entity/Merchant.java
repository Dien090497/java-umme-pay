package vn.unicloud.umeepay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import vn.unicloud.umeepay.enums.MerchantStatus;
import vn.unicloud.umeepay.enums.TransactionStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = Merchant.COLLECTION_NAME)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Merchant {

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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "merchant")
    private Credential credential;

    private LocalDateTime createDateTime;

    private String accountNo;

    @Column(name = "name", nullable = false)
    private String name;

    private String webhookUrl;

    private String webhookApiKey;

    @Override
    public String toString() {
        return "Merchant{" +
            "id='" + id + '\'' +
            ", status=" + status +
            ", user=[user]" +
            ", credential=[credential]" +
            ", createDateTime=" + createDateTime +
            ", accountNo='" + accountNo + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
