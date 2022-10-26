package vn.unicloud.umeepay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTPKey {

    private String otp;
    private String phone;
    private String sessionId;

}
