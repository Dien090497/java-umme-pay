package vn.unicloud.umeepay.entity;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = ResponseMessage.COLLECTION_NAME)
@Slf4j
@Audited
public class ResponseMessage extends Auditable<String>{

    public static final String COLLECTION_NAME = "response_message";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer code;

    private String viContent;

    private String enContent;

    private String description;

}
