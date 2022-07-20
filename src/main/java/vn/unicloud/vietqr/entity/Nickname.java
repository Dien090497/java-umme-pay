package vn.unicloud.vietqr.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import vn.unicloud.vietqr.enums.Branch;
import vn.unicloud.vietqr.enums.INickStatus;

import java.time.LocalDateTime;

@Document(collection = Nickname.COLLECTION_NAME)
@Getter
@Setter
@ToString
public class Nickname {

    public static final String COLLECTION_NAME = "nickname";

    @Id
    private String id;

    @Field("nickname")
    @Indexed(unique = true)
    private String nickname;

    @Field("branch")
    private Branch branch;

    @Field("create_date")
    private LocalDateTime createDate = LocalDateTime.now();

    @Field("account_no")
    private String accountNo;

    @Field("status")
    private INickStatus status;

}
