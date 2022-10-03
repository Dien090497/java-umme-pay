package vn.unicloud.umeepay.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.core.ResponseData;
import vn.unicloud.umeepay.dtos.payment.request.CancelTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.request.EncryptedBodyRequest;
import vn.unicloud.umeepay.dtos.payment.response.EncryptBodyResponse;
import vn.unicloud.umeepay.entity.Credential;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.CredentialRepository;
import vn.unicloud.umeepay.utils.CommonUtils;

import static org.keycloak.util.JsonSerialization.mapper;

@Service
@Log4j2
public class SecurityService {

    @Value("${umeepay.max-timestamp-diff-ms}")
    private Long timeStamp;

    @Autowired
    private CredentialRepository credentialRepository;

    public <T extends BaseRequestData> T authenticate(EncryptedBodyRequest requestData, Class<T> tClass) {
        // check timestamp
        if (System.currentTimeMillis() - requestData.getTimestamp() > timeStamp) {
            throw new InternalException(ResponseCode.TRANSACTION_EXPIRED);
        }
        // check signature
        try {
            Credential credential = credentialRepository.findById(requestData.getClientId()).orElseThrow(
                () -> {
                    throw new InternalException(ResponseCode.INVALID_KEY_ID);
                }
            );
            String signature = CommonUtils.md5(requestData.getClientId() +
                requestData.getTimestamp() +
                requestData.getData() +
                credential.getSecretKey());
            if (signature != null && signature.equals(requestData.getSignature())) {
                // Decrypt Data
                T requesResult =  mapper.readValue(CommonUtils.decryptAES(requestData.getData(), credential.getSecretKey()),tClass);
                requesResult.setTimestamp(requesResult.getTimestamp());
                requesResult.setClientId(requesResult.getClientId());
                requesResult.setCredential(credential);
                return requesResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new InternalException(ResponseCode.INVALID_DATA);
    }

    public void simpleAuthenticate(BaseRequestData request) {
        Credential credential = credentialRepository.findById(request.getKeyId()).orElseThrow(
            () -> {throw new InternalException(ResponseCode.INVALID_KEY_ID);}
        );
        if (!credential.getSecretKey().equals(request.getSecretKey())) {
            throw new InternalException(ResponseCode.AUTHORIZATION_FAILED);
        }
        log.debug("credential: {}", credential);
        request.setCredential(credential);
    }

    public <T extends ResponseData> ResponseEntity<ResponseBase<EncryptBodyResponse>> encryptResponse(EncryptedBodyRequest request, ResponseEntity<ResponseBase<T>> response){

        //get secretkey
        String secretKey = request.getCredential().getSecretKey();
        String xApiClient = request.getClientId();
        Long xApiTime = System.currentTimeMillis();
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        EncryptBodyResponse encryptBodyResponse = new EncryptBodyResponse();

        //encrypt data body
        T dataBody = response.getBody().getData();
        log.info("Data body: {}", dataBody);
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(dataBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new InternalException(ResponseCode.CANT_MAPPING_JSON_OBJECT);
        }
        String encryptData = CommonUtils.encryptAES(jsonString, secretKey);
        encryptBodyResponse.setData(encryptData);

        //encrypt header validation;
        String xApiValidate = CommonUtils.md5(xApiClient
                + xApiTime
                + encryptData
                + secretKey);

        //Set header
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-api-client", xApiClient);
        responseHeaders.set("x-api-time", String.valueOf(xApiTime));
        responseHeaders.set("x-api-validate", xApiValidate);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(new ResponseBase<>(encryptBodyResponse));

    }

}
