package vn.unicloud.umeepay.dtos.model;

import lombok.*;
import vn.unicloud.umeepay.entity.*;
import vn.unicloud.umeepay.enums.BusinessProduct;
import vn.unicloud.umeepay.enums.BusinessType;
import vn.unicloud.umeepay.enums.RevenueType;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {

    private BusinessType businessType; // Loại hình kinh doanh

    private List<BusinessProduct> businessSectors; // Lĩnh vực kinh doanh

    private String businessItems; // Các mặt hàng kinh doanh

    private Long transactionMaxAmount;

    private RevenueType revenueType;

    private String contactEmail;

    private String websiteUrl;

    private String storeAddress;

    private String companyName;

    private String companyAddress;

    private String companyPhone;

    private IdentifyInfoDto representativeInfo;

    private IdentifyInfoDto ownerInfo;

    private EkycDto repEkyc;

    private EkycDto ownerEkyc;

    private List<Document> documents;

    private List<BusinessOwner> owners;

    public ProfileDto(Profile profile) {
        this.businessType = profile.getBusinessType();
        this.businessSectors = profile.getBusinessSectors();
        this.businessItems = profile.getBusinessItems();
        this.transactionMaxAmount = profile.getTransactionMaxAmount();
        this.revenueType = profile.getRevenueType();
        this.contactEmail = profile.getContactEmail();
        this.websiteUrl = profile.getWebsiteUrl();
        this.storeAddress = profile.getStoreAddress();
        this.companyName = profile.getCompanyName();
        this.companyAddress = profile.getCompanyAddress();
        this.companyPhone = profile.getCompanyPhone();
        IdentifyInfo identifyInfo = profile.getRepresentativeInfo();
        if (identifyInfo != null) {
            this.representativeInfo = new IdentifyInfoDto(identifyInfo);
        }
        identifyInfo = profile.getOwnerInfo();
        if (identifyInfo != null) {
            this.ownerInfo = new IdentifyInfoDto(identifyInfo);
        }
        Ekyc ekyc = profile.getRepEkyc();
        if (repEkyc != null) {
            this.repEkyc = new EkycDto(ekyc);
        }
        ekyc = profile.getOwnerEkyc();
        if (ekyc != null) {
            this.ownerEkyc = new EkycDto(ekyc);
        }
        this.documents = profile.getDocuments();
        this.owners = profile.getOwners();
    }

}
