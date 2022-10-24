package vn.unicloud.umeepay.cache;

import lombok.Data;
import vn.unicloud.umeepay.enums.KeyStatus;

import java.time.LocalDateTime;

@Data
public class CredentialCache {
    private String id;

    private KeyStatus status;

    private LocalDateTime createDateTime;

    private String publicKey;

    private String secretKey;

    private String clientId;
}
