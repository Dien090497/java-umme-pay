package vn.unicloud.umeepay.entity;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.enums.Gender;
import vn.unicloud.umeepay.enums.IdentifyType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@Table(name = IdentifyInfo.COLLECTION_NAME)
public class IdentifyInfo {

    public static final String COLLECTION_NAME = "identity_info";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String frontUrl;

    private String backUrl;

    private String name;

    private String address;

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

    private String optionalData;

    private String idCheck;

    private String idLogic;

    private String idLogicMessage;
    
    private LocalDateTime createAt;

}
