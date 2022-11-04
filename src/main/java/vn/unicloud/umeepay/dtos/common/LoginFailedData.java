package vn.unicloud.umeepay.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class LoginFailedData implements Serializable {
    private LocalDateTime blockedAt = LocalDateTime.now();
    private Integer totalTimes = 0;
}
