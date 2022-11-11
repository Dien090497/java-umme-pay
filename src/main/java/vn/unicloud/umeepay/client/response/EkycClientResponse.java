package vn.unicloud.umeepay.client.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * User for mapping from API EKYC Service
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EkycClientResponse {

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

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;

    private String ethnicity;

    private String religion;

    private String sex;

    private String no;

    private String idType;

    private String passportType;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate issueDate;

    private String issueBy;

    private String documentId;

    private String optionalData;

    private String idCheck;

    private String idLogic;

    private String idLogicMessage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSz")
    private LocalDateTime createAt;

}
