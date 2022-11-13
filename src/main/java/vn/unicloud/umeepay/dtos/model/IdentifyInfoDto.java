package vn.unicloud.umeepay.dtos.model;

import lombok.*;
import vn.unicloud.umeepay.entity.Ekyc;
import vn.unicloud.umeepay.entity.IdentifyInfo;
import vn.unicloud.umeepay.enums.Gender;
import vn.unicloud.umeepay.enums.IdentifyType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IdentifyInfoDto {

    private Long id;

    private String frontUrl;

    private String backUrl;

    private String name;

    private Gender gender;

    private String idNumber;

    private IdentifyType type;

    private LocalDate issueDate;

    private String issueBy;

    private String address;

    private String nation;

    public IdentifyInfoDto(IdentifyInfo identifyInfo) {
        this.id = identifyInfo.getId();
        this.frontUrl = identifyInfo.getFrontUrl();
        this.backUrl = identifyInfo.getBackUrl();
        this.name = identifyInfo.getName();
        this.gender = identifyInfo.getGender();
        this.idNumber = identifyInfo.getIdNumber();
        this.type = identifyInfo.getType();
        this.issueDate = identifyInfo.getIssueDate();
        this.issueBy = identifyInfo.getIssueBy();
        this.address = identifyInfo.getAddress();
        this.nation = identifyInfo.getNation();
    }

}
