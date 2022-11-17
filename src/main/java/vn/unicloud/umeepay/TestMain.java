package vn.unicloud.umeepay;

import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.decoder.mpm.MerchantPresentedModeDecoder;
import com.emv.qrcode.model.mpm.*;
import vn.unicloud.umeepay.utils.CommonUtils;

public class TestMain {

    private static AdditionalDataFieldTemplate getAddtionalDataField() {
        final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific();
        paymentSystemSpecific.setGloballyUniqueIdentifier("1");
        paymentSystemSpecific.addPaymentSystemSpecific(new TagLengthString("01", "i"));

        final PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = new PaymentSystemSpecificTemplate();
        paymentSystemSpecificTemplate.setTag("50");
        paymentSystemSpecificTemplate.setValue(paymentSystemSpecific);

        final AdditionalDataField additionalDataFieldValue = new AdditionalDataField();
        additionalDataFieldValue.setAdditionalConsumerDataRequest("tuvxy");
        additionalDataFieldValue.setBillNumber("12345");
        additionalDataFieldValue.setCustomerLabel("fghij");
        additionalDataFieldValue.setLoyaltyNumber("54321");
        additionalDataFieldValue.setMobileNumber("67890");
        additionalDataFieldValue.setPurposeTransaction("pqres");
        additionalDataFieldValue.setReferenceLabel("abcde");
        additionalDataFieldValue.setStoreLabel("09876");
        additionalDataFieldValue.setTerminalLabel("klmno");
        additionalDataFieldValue.addPaymentSystemSpecific(paymentSystemSpecificTemplate);

        final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
        additionalDataField.setValue(additionalDataFieldValue);

        return additionalDataField;
    }

    // Merchant Account Information Template (IDs "26" to "51")
    // 3853 0010 A000000727 0123 0006970432 0109179052679 0208QRIBFTTA
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


    public static void main(String[] args) {
        String input = "Xin chào Trần Thanh Lộc 123";
        System.out.println("normalize: " + CommonUtils.deAccent(input));


        String key = CommonUtils.getEncryptKey(256);
        String data = "hello";
        String encrypted = CommonUtils.encryptAES(data, key);
        System.out.println("encrypt: " + encrypted);
        System.out.println("decrypted: " + CommonUtils.decryptAES(encrypted, key));
        //               00020101021138530010A0000007270123000697043201091790526790208QRIBFTTA53037045802VN63049D67
//        String source = "00020101021138530010A0000007270123000697043201091790526790208QRIBFTTA53037045802VN63049D67";
//        String source = "00020101021238630010A00000072701330006970452011910000322072901078400208QRIBFTTA53037045405500005802VN62240820dong qop quy vac xin63043A65";
//        System.out.println("source: " + source);
//        MerchantPresentedMode res = MerchantPresentedModeDecoder.decode(source, MerchantPresentedMode.class);
//        System.out.println("country code: " + res.getCountryCode());
//        System.out.println("getTransactionCurrency: " + res.getTransactionCurrency());
//        System.out.println("getAdditionalDataField: " + res.getAdditionalDataField());
//        System.out.println("getPayloadFormatIndicator: " + res.getPayloadFormatIndicator());
//        System.out.println("getPointOfInitiationMethod: " + res.getPointOfInitiationMethod());
//        System.out.println("getMerchantAccountInformation: " + res.getMerchantAccountInformation());
//        int amount = 10000;
//        MerchantPresentedMode code = new MerchantPresentedMode();
//        code.setCountryCode("VN");
//        code.setTransactionCurrency("704");
//        AdditionalDataField additionalDataField = new AdditionalDataField();
//        additionalDataField.setPurposeTransaction("dong qop quy vac xin nek");
//        AdditionalDataFieldTemplate additionalDataFieldTemplate = new AdditionalDataFieldTemplate();
//        additionalDataFieldTemplate.setValue(additionalDataField);
//        code.setAdditionalDataField(additionalDataFieldTemplate);
//        code.setPayloadFormatIndicator("01");
//        code.setTransactionAmount(String.valueOf(amount));
//        code.setPointOfInitiationMethod("11");
//        code.addMerchantAccountInformation(getMerchanAccountInformationReservedAdditional("970432", "179052679"));
//
//        System.out.println("data: " + code);
//        System.out.println("info: " + code.getMerchantAccountInformation());
    }
}
