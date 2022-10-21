package vn.unicloud.umeepay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import vn.unicloud.umeepay.enums.KeyStatus;
import vn.unicloud.umeepay.enums.UserStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = User.COLLECTION_NAME)
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public static final String COLLECTION_NAME = "user";

    @Id
    @Column(unique = true, nullable = false)
    private String id;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private LocalDateTime createDateTime;

    @OneToOne(mappedBy = "user")
    private Merchant merchant;

    private String accountNo;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

}
