package vn.unicloud.umeepay.entity.common;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

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

    private Map<String, String> content;

    private String description;

}
