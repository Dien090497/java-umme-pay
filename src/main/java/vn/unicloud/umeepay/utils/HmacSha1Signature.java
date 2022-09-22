package vn.unicloud.umeepay.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Formatter;

public class HmacSha1Signature {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();

        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        return formatter.toString();
    }

    public static String calculateRFC2104HMAC(String data, String key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
    {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        //return toHexString(mac.doFinal(data.getBytes()));
        return Base64.getEncoder().encodeToString(mac.doFinal(data.getBytes()));
    }

    public static void main(String[] args) throws Exception {
        String hmac = calculateRFC2104HMAC("1515TM15#9499687306794080ad46e512cd63e0d6C9FBD922-FCCE-46FD-BD36-96A2D732FBBE#08/06/2021 17:41:27#100000#44363636",
                "9499687306794080ad46e512cd63e0d6C9FBD922-FCCE-46FD-BD36-96A2D732FBBE");

        System.out.println(hmac);
    }
}
