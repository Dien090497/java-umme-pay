package vn.unicloud.umeepay.entity.merchant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import vn.unicloud.umeepay.enums.BusinessType;
import vn.unicloud.umeepay.enums.RevenueType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = Profile.COLLECTION_NAME)
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    public static final String COLLECTION_NAME = "profile";

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
    private BusinessType businessType; // Loại hình kinh doanh

    private String businessSector; // Lĩnh vực kinh doanh

    private String businessItems; // Các mặt hàng kinh doanh

    private Long transactionMaxAmount;

    @Enumerated(EnumType.STRING)
    private RevenueType revenueType;

    private String websiteUrl;

    private String storeAddress;

    private String companyName;

    private String companyAddress;

    private String companyPhone;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private IdentifyCard representativeId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private IdentifyCard ownerId;

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", businessType=" + businessType +
                ", businessSector='" + businessSector + '\'' +
                ", businessItems='" + businessItems + '\'' +
                ", transactionMaxAmount=" + transactionMaxAmount +
                ", revenueType=" + revenueType +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                '}';
    }
}
