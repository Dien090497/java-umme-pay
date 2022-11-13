package vn.unicloud.umeepay.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OTPKey {

    private String otp;
    private String phone;
    private String sessionId;
    private String signature;

}
