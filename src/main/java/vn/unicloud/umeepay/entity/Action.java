package vn.unicloud.umeepay.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = Action.COLLECTION_NAME)
@Accessors(chain = true)
public class Action {

    public static final String COLLECTION_NAME = "action";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

}
