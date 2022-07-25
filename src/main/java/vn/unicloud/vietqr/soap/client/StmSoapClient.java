package vn.unicloud.vietqr.soap.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;
import vn.unicloud.vietqr.soap.inetservice.model.*;
import vn.unicloud.vietqr.utils.HmacSha1Signature;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class StmSoapClient extends WebServiceGatewaySupport {
    @Value("${soap.part-one-hard-key}")
    public String partOneHardKey;

    @Value("${soap.part-two-access-key}")
    public String partTwoAccessKey;

    @Value("${soap.access-key}")
    public String accessKey;

    /**
     * Rút tiền từ STM
     */
    public STMWithDrawalResponse stmWithdrawal(String url, STMWithDrawal request, String soapAction) {
        return (STMWithDrawalResponse) getWebServiceTemplate().marshalSendAndReceive(url, request, message -> {
            var soapMessage = (SoapMessage) message;
            var header = soapMessage.getSoapHeader();
            ((SoapMessage) message).setSoapAction(soapAction);
            var objectFactory = new ObjectFactory();
            var securityHeader = objectFactory.createSecurityHeader();
            securityHeader.setAccessKey(accessKey);
            String dateTime = formatLocalDateTime(LocalDateTime.now(), "dd/MM/yyyy HH:mm:ss");
            securityHeader.setTimestamp(dateTime);
            securityHeader.setStmId(request.getTerminalId());
            securityHeader.setAction(soapAction);
            String fullKey = partOneHardKey + partTwoAccessKey;
            String dataToSign = request.getTerminalId() + "#" +
                    fullKey + "#" +
                    dateTime + "#" +
                    request.getTransAmount() + "#" +
                    request.getAccountNo();
            log.info("Soap call: data to sign {}", dataToSign);
            log.info("Soap call: full key {}", fullKey);
            String signature = null;
            try {
                signature = HmacSha1Signature.calculateRFC2104HMAC(dataToSign, fullKey);
                securityHeader.setSignature(signature);
            } catch (SignatureException | InvalidKeyException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            JAXBElement<SecurityHeader> headers = objectFactory.createSecurityHeader(securityHeader);
            try {
                var context = JAXBContext.newInstance(SecurityHeader.class);
                var marshaller = context.createMarshaller();
                // marshal the headers into the specified result
                marshaller.marshal(headers, header.getResult());
                log.info("Soap call: Created header: {}", securityHeader.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Hoàn Tiền
     */
    public STMWithDrawalRevResponse stmWithdrawalRecovery(String url, STMWithDrawalRev request, String soapAction) {
        return (STMWithDrawalRevResponse) getWebServiceTemplate().marshalSendAndReceive(url, request, message -> {
            var soapMessage = (SoapMessage) message;
            var header = soapMessage.getSoapHeader();
            ((SoapMessage) message).setSoapAction(soapAction);
            var objectFactory = new ObjectFactory();
            var securityHeader = objectFactory.createSecurityHeader();
            securityHeader.setAccessKey(accessKey);
            String dateTime = formatLocalDateTime(LocalDateTime.now(), "dd/MM/yyyy HH:mm:ss");
            securityHeader.setTimestamp(dateTime);
            securityHeader.setStmId(request.getTerminalId());
            securityHeader.setAction(soapAction);
            String fullKey = partOneHardKey + partTwoAccessKey;
            String dataToSign = request.getTerminalId() + "#" +
                    fullKey + "#" +
                    dateTime + "#" +
                    request.getTransAmount() + "#" +
                    request.getAccountNo();
            log.info("Soap call: data to sign {}", dataToSign);
            log.info("Soap call: full key {}", fullKey);
            String signature = null;
            try {
                signature = HmacSha1Signature.calculateRFC2104HMAC(dataToSign, fullKey);
                securityHeader.setSignature(signature);
            } catch (SignatureException | InvalidKeyException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            JAXBElement<SecurityHeader> headers = objectFactory.createSecurityHeader(securityHeader);
            try {
                var context = JAXBContext.newInstance(SecurityHeader.class);
                var marshaller = context.createMarshaller();
                // marshal the headers into the specified result
                marshaller.marshal(headers, header.getResult());
                log.info("Soap call: Created header: {}", securityHeader.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Get thông tin thẻ
     */
    public STMGetAccountByCardNoResponse getAccountInfo(String url, String stmId, STMGetAccountByCardNo request, String soapAction) {
        return (STMGetAccountByCardNoResponse) getWebServiceTemplate().marshalSendAndReceive(url, request, message -> {
            var soapMessage = (SoapMessage) message;
            var header = soapMessage.getSoapHeader();
            ((SoapMessage) message).setSoapAction(soapAction);
            var objectFactory = new ObjectFactory();
            var securityHeader = objectFactory.createSecurityHeader();
            securityHeader.setAccessKey(partTwoAccessKey);
            String dateTime = formatLocalDateTime(LocalDateTime.now(), "dd/MM/yyyy HH:mm:ss");
            securityHeader.setTimestamp(dateTime);
            securityHeader.setStmId(stmId);
            securityHeader.setAction(soapAction);
            String fullKey = partOneHardKey + partTwoAccessKey;
            String dataToSign = stmId + "#" +
                    fullKey + "#" +
                    dateTime + "#" +
                    '0' + "#";
            log.info("Soap call: data to sign {}", dataToSign);
            log.info("Soap call: full key {}", fullKey);
            String signature = null;
            try {
                signature = HmacSha1Signature.calculateRFC2104HMAC(dataToSign, fullKey);
                securityHeader.setSignature(signature);
            } catch (SignatureException | InvalidKeyException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            JAXBElement<SecurityHeader> headers = objectFactory.createSecurityHeader(securityHeader);
            try {
                var context = JAXBContext.newInstance(SecurityHeader.class);
                var marshaller = context.createMarshaller();
                // marshal the headers into the specified result
                marshaller.marshal(headers, header.getResult());
                log.info("Soap call: Created header: {}", securityHeader.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Nộp tiền vào tài khoản từ máy
     */
    public STMDepositCashResponse stmDepositCash(String url,String stmId ,STMDepositCash request, String soapAction){
        return (STMDepositCashResponse) getWebServiceTemplate().marshalSendAndReceive(url, request, message -> {
            var soapMessage = (SoapMessage) message;
            var header = soapMessage.getSoapHeader();
            ((SoapMessage) message).setSoapAction(soapAction);
            JAXBElement<SecurityHeader> headers = getSign(stmId, soapAction, request.getTranAmount(), request.getToCkAcctNbr());
            try {
                var context = JAXBContext.newInstance(SecurityHeader.class);
                var marshaller = context.createMarshaller();
                // marshal the headers into the specified result
                marshaller.marshal(headers, header.getResult());
                log.info("Soap call: Created header: {}", header.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Lấy thông tin account từ số thẻ
     */
    public STMGetAccountByCardNoResponse getAccountByCardNo(String url,String stmId, STMGetAccountByCardNo  request, String soapAction){
        return (STMGetAccountByCardNoResponse) getWebServiceTemplate().marshalSendAndReceive(url, request, message -> {
            var soapMessage = (SoapMessage) message;
            var header = soapMessage.getSoapHeader();
            ((SoapMessage) message).setSoapAction(soapAction);
            JAXBElement<SecurityHeader> headers = getSign(stmId, soapAction);
            try {
                var context = JAXBContext.newInstance(SecurityHeader.class);
                var marshaller = context.createMarshaller();
                // marshal the headers into the specified result
                marshaller.marshal(headers, header.getResult());
                log.info("Soap call: Created header: {}", header.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private String formatLocalDateTime(LocalDateTime localDate, String pattern) {
        try {
            var formatters = DateTimeFormatter.ofPattern(pattern);
            return localDate.format(formatters);

        } catch (Exception ignored) {
            return StringUtils.EMPTY;
        }
    }

    private JAXBElement<SecurityHeader> getSign(String stmId, String soapAction){
        var objectFactory = new ObjectFactory();
        var securityHeader = objectFactory.createSecurityHeader();
        securityHeader.setAccessKey(partTwoAccessKey);
        String dateTime = formatLocalDateTime(LocalDateTime.now(), "dd/MM/yyyy HH:mm:ss");
        securityHeader.setTimestamp(dateTime);
        securityHeader.setStmId(stmId);
        securityHeader.setAction(soapAction);
        String fullKey = partOneHardKey + partTwoAccessKey;
        String dataToSign = stmId + "#" +
                fullKey + "#" +
                dateTime + "#" +
                '0' + "#";
        log.info("Soap call: data to sign {}", dataToSign);
        log.info("Soap call: full key {}", fullKey);
        String signature = null;
        try {
            signature = HmacSha1Signature.calculateRFC2104HMAC(dataToSign, fullKey);
            securityHeader.setSignature(signature);
        } catch (SignatureException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return objectFactory.createSecurityHeader(securityHeader);
    }

    private JAXBElement<SecurityHeader> getSign(String stmId, String soapAction, BigDecimal transactionAmount, String accountNo){
        var objectFactory = new ObjectFactory();
        var securityHeader = objectFactory.createSecurityHeader();
        securityHeader.setAccessKey(partTwoAccessKey);
        String dateTime = formatLocalDateTime(LocalDateTime.now(), "dd/MM/yyyy HH:mm:ss");
        securityHeader.setTimestamp(dateTime);
        securityHeader.setStmId(stmId);
        securityHeader.setAction(soapAction);
        String fullKey = partOneHardKey + partTwoAccessKey;
        String dataToSign = stmId + "#" +
                fullKey + "#" +
                dateTime + "#" +
                transactionAmount + "#" +
                accountNo;
        log.info("Soap call: data to sign {}", dataToSign);
        log.info("Soap call: full key {}", fullKey);
        String signature = null;
        try {
            signature = HmacSha1Signature.calculateRFC2104HMAC(dataToSign, fullKey);
            securityHeader.setSignature(signature);
        } catch (SignatureException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return objectFactory.createSecurityHeader(securityHeader);
    }
}
