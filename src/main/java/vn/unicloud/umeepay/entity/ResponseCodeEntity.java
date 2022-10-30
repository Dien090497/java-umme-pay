package vn.unicloud.umeepay.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Map;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = ResponseCodeEntity.COLLECTION_NAME)
public class ResponseCodeEntity extends Auditable<String>{

    public static final String COLLECTION_NAME = "response_code";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer code;

    private String viContent;

    private String enContent;

    private String description;

}
