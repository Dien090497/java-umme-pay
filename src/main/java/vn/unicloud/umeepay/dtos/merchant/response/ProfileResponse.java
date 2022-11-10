package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.*;
import vn.unicloud.umeepay.enums.BusinessType;
import vn.unicloud.umeepay.enums.RevenueType;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class ProfileResponse extends BaseResponseData {

    private String id;

    private String name;

    private BusinessType businessType;

    private String businessSector;

    private String businessItems;

    private Long transactionMaxAmount;

    private RevenueType revenueType;

    private String websiteUrl;

    private String storeAddress;

    private String companyName;

    private String companyAddress;

    private String companyPhone;

    private IdentifyInfo repIdInfo;

    private IdentifyInfo ownerIdInfo;

    private List<Document> documents;

    private List<BusinessOwner> owners;

    public ProfileResponse(Profile profile) {
        if (profile == null) {
            return;
        }

        this.id = profile.getId();
        this.name = profile.getName();
        this.businessType = profile.getBusinessType();
        this.businessSector = profile.getBusinessSector();
        this.businessItems = profile.getBusinessItems();
        this.transactionMaxAmount = profile.getTransactionMaxAmount();
        this.revenueType = profile.getRevenueType();
        this.websiteUrl = profile.getWebsiteUrl();
        this.storeAddress = profile.getStoreAddress();
        this.companyName = profile.getCompanyName();
        this.companyAddress = profile.getCompanyAddress();
        this.companyPhone = profile.getCompanyPhone();
        this.owners = profile.getOwners();
        this.repIdInfo = profile.getRepresentativeInfo();
        this.ownerIdInfo = profile.getOwnerInfo();
        this.documents = profile.getDocuments();

    }

}
