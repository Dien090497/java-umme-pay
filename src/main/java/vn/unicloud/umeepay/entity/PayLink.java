package vn.unicloud.umeepay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.GenericGenerator;
import vn.unicloud.umeepay.enums.CustomerInfoType;
import vn.unicloud.umeepay.enums.PaymentExpireType;
import vn.unicloud.umeepay.enums.TransactionStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = PayLink.COLLECTION_NAME)
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class PayLink {

    public static final String COLLECTION_NAME = "pay_link";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private String code;

    private String description;

    private String fileUrl;

    private PaymentExpireType expireType;

    private CustomerInfoType customerInfoType;

    private boolean showName;

    private boolean showAddress;

    private boolean showPhone;

    private boolean showEmail;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    @JsonIgnore
    private Transaction transaction;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    @JsonIgnore
    private Merchant merchant;

}
