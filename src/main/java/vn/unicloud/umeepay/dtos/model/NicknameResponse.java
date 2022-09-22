package vn.unicloud.umeepay.dtos.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import vn.unicloud.umeepay.enums.Branch;
import vn.unicloud.umeepay.enums.INickStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class NicknameResponse {

    private String id;

    private String nickname;

    private Branch branch;

    private LocalDateTime createDate;

    private String accountNo;

    private INickStatus status;

}
