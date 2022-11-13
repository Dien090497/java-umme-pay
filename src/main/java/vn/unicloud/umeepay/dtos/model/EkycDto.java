package vn.unicloud.umeepay.dtos.model;

import lombok.*;
import vn.unicloud.umeepay.entity.*;
import vn.unicloud.umeepay.enums.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EkycDto {

    private Long id;

    private String frontUrl;

    private String backUrl;

    private String name;

    private String sex;

    private String no;

    private String idType;

    private LocalDate issueDate;

    private String issueBy;

    private String address;

    private String national;

    public EkycDto(Ekyc ekyc) {
        this.id = ekyc.getId();
        this.frontUrl = ekyc.getFrontUrl();
        this.backUrl = ekyc.getBackUrl();
        this.name = ekyc.getName();
        this.sex = ekyc.getSex();
        this.no = ekyc.getNo();
        this.idType = ekyc.getIdType();
        this.issueDate = ekyc.getIssueDate();
        this.issueBy = ekyc.getIssueBy();
        this.address = ekyc.getAddress();
        this.national = ekyc.getNational();
    }

}
