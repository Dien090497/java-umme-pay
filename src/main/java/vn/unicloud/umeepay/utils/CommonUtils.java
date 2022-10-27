package vn.unicloud.umeepay.utils;

import com.emv.qrcode.model.mpm.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Hex;
import org.keycloak.TokenVerifier;
import org.keycloak.representations.AccessToken;
import vn.unicloud.umeepay.enums.TransactionStatus;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Log4j2
public class CommonUtils {

    private static final String hardCodeOTP = "123456";

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getOTP(boolean isHardCode) {
        if (isHardCode) {
            return hardCodeOTP;
        }
        try {
            Random random = SecureRandom.getInstanceStrong();
            return String.format("%06d", random.nextInt(999999));
        } catch (Exception e) {
            log.error("generate code error: {}", e.getMessage());
        }
        return hardCodeOTP;
    }

    public static String getSecureRandomKey(int keySize) {
        byte[] secureRandomKeyBytes = new byte[keySize];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(secureRandomKeyBytes);
        Key key = new SecretKeySpec(secureRandomKeyBytes, "AES");
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static String getEncryptKey(int keySize) {
        byte[] secureRandomKeyBytes = new byte[keySize / 8];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(secureRandomKeyBytes);
        Key key = new SecretKeySpec(secureRandomKeyBytes, "AES");
        return Hex.encodeHexString(key.getEncoded()).toUpperCase();
    }

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            return Hex.encodeHexString(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encryptAES(String data, String key) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            Key priKey = new SecretKeySpec(Hex.decodeHex(key), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, priKey);
            byte[] plainText = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptAES(String data, String key) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            Key priKey = new SecretKeySpec(Hex.decodeHex(key), "AES");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(data));
            return new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getCheckDigit(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {

            // Get the digit at the current position.
            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        // The check digit is the number required to make the sum a multiple of
        // 10.
        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }

    public static String generateVirtualAccount(String prefix) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        long number = ThreadLocalRandom.current().nextInt(999999);
        String numberFormat = String.format("%06d", number);
        StringBuilder res = new StringBuilder();
        res.append(prefix);
        res.append(LocalDate.now().format(dateTimeFormatter));
        res.append(numberFormat);
        res.append(CommonUtils.getCheckDigit(res.toString()));
        return res.toString();
    }

    private static MerchantAccountInformationTemplate getMerchanAccountInformationReservedAdditional(String bin, String accountNo) {
        final MerchantAccountInformationReservedAdditional merchantAccountInformationValue = new MerchantAccountInformationReservedAdditional();
        MerchantAccountInformationReservedAdditional additional = new MerchantAccountInformationReservedAdditional();
        additional.setGloballyUniqueIdentifier(bin);
        additional.addPaymentNetworkSpecific("01", accountNo);
        merchantAccountInformationValue.setGloballyUniqueIdentifier("A000000727");
        merchantAccountInformationValue.addPaymentNetworkSpecific("01", additional.toString());
        merchantAccountInformationValue.addPaymentNetworkSpecific("02", "QRIBFTTA");
        return new MerchantAccountInformationTemplate("38", merchantAccountInformationValue);
    }

    public static String normalizeTerminalLocation(String input) {
        if (input == null) {
            return "";
        }
        return input.substring(0, Math.min(input.length(), 70));
    }

    public static String generateQRCode(String bin, String targetAccount, long amount, String content) {
        MerchantPresentedMode code = new MerchantPresentedMode();
        code.setCountryCode("VN");
        code.setTransactionCurrency("704");
        code.setPayloadFormatIndicator("01");
        code.setTransactionAmount(String.valueOf(amount));
        code.setPointOfInitiationMethod("12");
        if (content != null) {
            AdditionalDataField additionalDataField = new AdditionalDataField();
            additionalDataField.setPurposeTransaction(content);
            AdditionalDataFieldTemplate additionalDataFieldTemplate = new AdditionalDataFieldTemplate();
            additionalDataFieldTemplate.setValue(additionalDataField);
            code.setAdditionalDataField(additionalDataFieldTemplate);
        }
        code.addMerchantAccountInformation(getMerchanAccountInformationReservedAdditional(bin, targetAccount));
        return code.toString();
    }

    public static String getContent(String billId) {
        String res = String.format("TT Don hang %s", billId);
        return res.substring(0, Math.min(100, res.length()));
    }

    public static boolean isExpired(Long since) {
        Long iNow = Instant.now().getEpochSecond();
        return iNow - since > 0;
    }

    public static String getPrefixByAccount(String account) {
        if (account == null || account.length() < 4) {
            return null;
        }
        return account.substring(0, 4);
    }

    public static TransactionStatus reformatStatus(String input) {
        if (input == null) {
            return null;
        }
        if (input.equals("SUCCESS")) {
            return TransactionStatus.SUCCESS;
        }
        return TransactionStatus.FAIL;
    }

    public static AccessToken getAccessToken(HttpServletRequest request) {
        try {
            String authorization = request.getHeader("Authorization");
            if (authorization != null) {
                String accessToken = authorization.substring("Bearer ".length());
                return TokenVerifier.create(accessToken, AccessToken.class).getToken();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getBasicToken(HttpServletRequest request) {
        try {
            String authorization = request.getHeader("Authorization");
            if (authorization != null) {
                return authorization.substring("Basic ".length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
