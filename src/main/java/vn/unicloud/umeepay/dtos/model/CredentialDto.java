package vn.unicloud.umeepay.dtos.model;

import lombok.*;
import vn.unicloud.umeepay.entity.BankAccount;
import vn.unicloud.umeepay.entity.Credential;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.Profile;
import vn.unicloud.umeepay.enums.KeyStatus;
import vn.unicloud.umeepay.enums.MerchantStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CredentialDto {

    private String id;

    private KeyStatus status;

    private LocalDateTime createdAt;

    private String publicKey;

    private String secretKey;

    private String clientId;

    public CredentialDto(Credential credential) {
        this.id = credential.getId();
        this.status = credential.getStatus();
        this.createdAt = credential.getCreatedAt();
        this.publicKey = credential.getPublicKey();
        this.secretKey = credential.getSecretKey();
        this.clientId = credential.getClientId();
    }

}
