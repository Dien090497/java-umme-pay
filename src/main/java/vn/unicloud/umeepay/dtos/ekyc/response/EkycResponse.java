package vn.unicloud.umeepay.dtos.ekyc.response;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.core.BaseResponse;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.Ekyc;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EkycResponse extends BaseResponseData {

    private Long id;

    private String frontUrl;

    private String backUrl;

    private String name;

    private String street;

    private String hometown;

    private String district;

    private String province;

    private String country;

    private String national;

    private LocalDate birthday;

    private String ethnicity;

    private String religion;

    private String sex;

    private String no;

    private String idType;

    private String passportType;

    private LocalDate issueDate;

    private String issueBy;

    private String documentId;

    private String optionalData;

    private String idCheck;

    private String idLogic;

    private String idLogicMessage;

    public EkycResponse(Ekyc ekyc) {
        if (ekyc == null) {
            return;
        }
        this.id = ekyc.getId();
        this.frontUrl = ekyc.getFrontUrl();
        this.backUrl = ekyc.getBackUrl();
        this.name = ekyc.getBackUrl();
        this.street = ekyc.getStreet();
        this.hometown = ekyc.getHometown();
        this.district = ekyc.getDistrict();
        this.province = ekyc.getProvince();
        this.country = ekyc.getCountry();
        this.national = ekyc.getNational();
        this.birthday = ekyc.getBirthday();
        this.ethnicity = ekyc.getEthnicity();
        this.religion = ekyc.getReligion();
        this.sex = ekyc.getSex();
        this.no = ekyc.getNo();
        this.idType = ekyc.getIdType();
        this.idCheck = ekyc.getIdType();
        this.passportType = ekyc.getPassportType();
        this.issueBy = ekyc.getIssueBy();
        this.issueDate = ekyc.getIssueDate();
        this.documentId = ekyc.getDocumentId();
        this.optionalData = ekyc.getOptionalData();
        this.idCheck = ekyc.getIdCheck();
        this.idLogic = ekyc.getIdLogic();
        this.idLogicMessage = ekyc.getIdLogicMessage();
    }

}
