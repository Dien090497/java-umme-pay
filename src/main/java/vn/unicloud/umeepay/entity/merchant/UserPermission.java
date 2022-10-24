package vn.unicloud.umeepay.entity.merchant;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = UserPermission.COLLECTION_NAME)
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPermission {
    public static final String COLLECTION_NAME = "user_permission";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
