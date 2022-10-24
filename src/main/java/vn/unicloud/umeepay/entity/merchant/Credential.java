package vn.unicloud.umeepay.entity.merchant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import vn.unicloud.umeepay.enums.KeyStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = Credential.COLLECTION_NAME)
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Credential {

    public static final String COLLECTION_NAME = "credential";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Enumerated(EnumType.STRING)
    private KeyStatus status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    @JsonIgnore
    private Merchant merchant;

    private LocalDateTime createdAt = LocalDateTime.now();

    private String publicKey;

    private String secretKey;

    private String clientId;

}
