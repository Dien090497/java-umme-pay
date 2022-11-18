package vn.unicloud.umeepay.dtos.merchant.response;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.dtos.admin.response.AdminResponse;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.enums.BusinessProduct;
import vn.unicloud.umeepay.enums.BusinessType;
import vn.unicloud.umeepay.enums.MerchantStatus;
import vn.unicloud.umeepay.enums.RevenueType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class MerchantResponse extends BaseResponseData {

    private String id;

    private MerchantStatus status;

    private String accountNo;

    private String webhookUrl;

    private String webhookApiKey;

    private LocalDateTime requestAt;

    private String username;

    private String name;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime modifiedAt;

    private String modifiedBy;

    private String disapprovedReason;

    private BusinessType businessType;

    private List<BusinessProduct> businessSector;

    private String businessItems;

    private AdminResponse approvedBy;

    private LocalDateTime approvedAt;


    public MerchantResponse(Merchant merchant) {
        if (merchant == null) {
            return;
        }

        this.status = merchant.getStatus();
        this.id = merchant.getId();
        this.accountNo = merchant.getAccountNo();
        this.webhookUrl = merchant.getWebhookUrl();
        this.webhookApiKey = merchant.getWebhookApiKey();
        this.requestAt = merchant.getRequestAt();
        this.approvedAt = merchant.getApprovedAt();
        this.name = merchant.getName();
        this.createdAt = merchant.getCreatedAt();
        this.createdBy = merchant.getCreatedBy();
        this.modifiedAt = merchant.getModifiedAt();
        this.modifiedBy = merchant.getModifiedBy();
        this.disapprovedReason = merchant.getDisapprovedReason();

        if (merchant.getProfile() != null) {
            this.businessType = merchant.getProfile().getBusinessType();
            this.businessSector = merchant.getProfile().getBusinessSectors();
            this.businessItems = merchant.getProfile().getBusinessItems();
        }

        if (merchant.getApprovedBy() != null) {
            this.approvedBy = new AdminResponse(merchant.getApprovedBy());
        }

        if (merchant.getUser() != null) {
            this.username = merchant.getUser().getUsername();
        }
    }

}
