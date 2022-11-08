package vn.unicloud.umeepay.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = BusinessOwner.COLLECTION_NAME)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class BusinessOwner {

    public static final String COLLECTION_NAME = "business_owner";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String identifyNumber;

    private Long funPercent;

    private String position;

}
