package vn.unicloud.umeepay.entity.common;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.enums.ResponseCodeGroup;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = ResponseCodeEntity.COLLECTION_NAME)
public class ResponseCodeEntity {

    public static final String COLLECTION_NAME = "response_code";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer code;

    private String contentCode;

    private String description;

    private ResponseCodeGroup group;

}
