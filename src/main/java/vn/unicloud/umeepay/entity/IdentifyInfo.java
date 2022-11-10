package vn.unicloud.umeepay.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.enums.Gender;
import vn.unicloud.umeepay.enums.IdentifyType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = IdentifyInfo.COLLECTION_NAME)
public class IdentifyInfo {

    public static final String COLLECTION_NAME = "identity_info";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String frontUrl;

    private String backUrl;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 6)
    private Gender gender;

    private String idNumber;

    @Enumerated(EnumType.STRING)
    private IdentifyType type;

    private LocalDate issueDate;

    private String issueBy;

    private String address;

    private String nation;

    @Override
    public String toString() {
        return "IdentifyCard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", idNumber='" + idNumber + '\'' +
                ", type=" + type +
                ", issueDate=" + issueDate +
                ", issueBy='" + issueBy + '\'' +
                ", address='" + address + '\'' +
                ", nation='" + nation + '\'' +
                '}';
    }
}
