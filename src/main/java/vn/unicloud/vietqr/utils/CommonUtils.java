package vn.unicloud.vietqr.utils;

import com.emv.qrcode.model.mpm.MerchantAccountInformationReservedAdditional;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;
import com.emv.qrcode.model.mpm.MerchantPresentedMode;
import org.keycloak.TokenVerifier;
import org.keycloak.representations.AccessToken;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

public class CommonUtils {

    public static String getSecureRandomKey(int keySize) {
        byte[] secureRandomKeyBytes = new byte[keySize];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(secureRandomKeyBytes);
        Key key = new SecretKeySpec(secureRandomKeyBytes, "AES");
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static int getCheckDigit(String number) {

        // Get the sum of all the digits, however we need to replace the value
        // of the first digit, and every other digit, with the same digit
        // multiplied by 2. If this multiplication yields a number greater
        // than 9, then add the two digits together to get a single digit
        // number.
        //
        // The digits we need to replace will be those in an even position for
        // card numbers whose length is an even number, or those is an odd
        // position for card numbers whose length is an odd number. This is
        // because the Luhn algorithm reverses the card number, and doubles
        // every other number starting from the second number from the last
        // position.
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
        long number = ThreadLocalRandom.current().nextInt(100000);
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

    public static String generateQRCode(String bin, String targetAccount, long amount) {
        MerchantPresentedMode code = new MerchantPresentedMode();
        code.setCountryCode("VN");
        code.setTransactionCurrency("704");
        code.setPayloadFormatIndicator("01");
        code.setTransactionAmount(String.valueOf(amount));
        code.setPointOfInitiationMethod("11");
        code.addMerchantAccountInformation(getMerchanAccountInformationReservedAdditional(bin, targetAccount));
        return code.toString();
    }

    public static boolean isExpired(Long since) {
        Long iNow = Instant.now().getEpochSecond() * 1000;
        return iNow - since > 0;
    }

    public static String getPrefixByAccount(String account) {
        if (account == null || account.length() < 6) {
            return null;
        }
        return account.substring(0, 6);
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
