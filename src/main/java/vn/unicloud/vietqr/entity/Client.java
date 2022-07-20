package vn.unicloud.vietqr.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import vn.unicloud.vietqr.enums.ClientStatus;

import java.time.LocalDateTime;

@Document(collection = Client.COLLECTION_NAME)
@Getter
@Setter
@ToString
public class Client {

    public static final String COLLECTION_NAME = "client";

    @Id
    private String id;

    @Field("client_id")
    @Indexed(unique = true)
    private String clientId;

    @Field("secret_key")
    private String secretKey;

    @Field("status")
    private ClientStatus status;

    @Field("branch_name")
    @Indexed(unique = true)
    private String branchName;

    @Field("callback_url")
    @Indexed(unique = true)
    private String callbackUrl;

    @Field("callback_cert")
    private String callbackCert;

    @Field("create_date")
    private LocalDateTime createDate = LocalDateTime.now();

}
