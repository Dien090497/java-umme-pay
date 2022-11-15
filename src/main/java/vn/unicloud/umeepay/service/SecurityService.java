package vn.unicloud.umeepay.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.core.ResponseData;
import vn.unicloud.umeepay.dtos.request.EncryptedBodyRequest;
import vn.unicloud.umeepay.dtos.response.EncryptBodyResponse;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.entity.Credential;
import vn.unicloud.umeepay.entity.RoleGroup;
import vn.unicloud.umeepay.entity.User;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.CredentialRepository;
import vn.unicloud.umeepay.utils.CommonUtils;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

@Service
@Log4j2
@RequiredArgsConstructor
public class SecurityService {

    @Value("${umeepay.max-timestamp-diff-ms}")
    private Long timeStamp;

    @Value("${umeepay.paygate.client-id}")
    private String paygateClientId;

    @Value("${umeepay.paygate.encrypt-key}")
    private String paygateEncryptKey;

    private final CredentialRepository credentialRepository;

    private final CredentialService credentialService;

    private final RedisService redisService;

    private final UserService userService;

    private final AdminService adminService;

    private final ObjectMapper objectMapper;

    public <T extends BaseRequestData> T authenticate(EncryptedBodyRequest requestData, Class<T> tClass) {
        // check timestamp
        if (System.currentTimeMillis() - requestData.getTimestamp() > timeStamp) {
            throw new InternalException(ResponseCode.TRANSACTION_EXPIRED);
        }
        // check signature
        try {
            Credential credentialCache = credentialService.getCredentialCacheById(requestData.getClientId());
            if (credentialCache == null) {
                throw new InternalException(ResponseCode.INVALID_KEY_ID);
            }
            String signature = CommonUtils.md5(requestData.getClientId() +
                requestData.getTimestamp() +
                requestData.getData() +
                credentialCache.getSecretKey());
            if (signature != null && signature.equals(requestData.getSignature())) {
                // Decrypt Data
                T requesResult =  objectMapper.readValue(CommonUtils.decryptAES(requestData.getData(), credentialCache.getSecretKey()),tClass);
                requesResult.setTimestamp(requesResult.getTimestamp());
                requesResult.setClientId(requesResult.getClientId());
                requesResult.setCredential(credentialCache);
                return requesResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new InternalException(ResponseCode.INVALID_DATA);
    }

    public <T extends BaseRequestData> T paygateAuthenticate(EncryptedBodyRequest requestData, Class<T> tClass) {
        // check timestamp
        if (System.currentTimeMillis() - requestData.getTimestamp() > timeStamp) {
            throw new InternalException(ResponseCode.TRANSACTION_EXPIRED);
        }
        // check signature
        try {
            if (!paygateClientId.equals(requestData.getClientId())) {
                log.error("Invalid paygate client id");
                throw new InternalException(ResponseCode.INVALID_CERTIFICATE);
            }
            String signature = CommonUtils.md5(requestData.getClientId() +
                requestData.getTimestamp() +
                requestData.getData() +
                paygateEncryptKey);
            if (signature != null && signature.equals(requestData.getSignature())) {
                // Decrypt Data
                T requestResult =  objectMapper.readValue(CommonUtils.decryptAES(requestData.getData(), paygateEncryptKey), tClass);
                requestResult.setTimestamp(requestResult.getTimestamp());
                requestResult.setClientId(requestResult.getClientId());
                return requestResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new InternalException(ResponseCode.INVALID_CERTIFICATE);
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
        log.debug("Data body: {}", dataBody);
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(dataBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new InternalException(ResponseCode.CANT_MAPPING_JSON_OBJECT);
        }
        String encryptData = CommonUtils.encryptAES(jsonString, secretKey);
        encryptBodyResponse.setData(encryptData);

        // encrypt header validation
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

    public <T extends ResponseData> ResponseEntity<ResponseBase<String>> encryptPaygateResponse(ResponseEntity<ResponseBase<T>> response){

        //get secretkey
        String secretKey = paygateEncryptKey;
        String xApiClient = paygateClientId;
        Long xApiTime = System.currentTimeMillis();

        //encrypt data body
        T dataBody = response.getBody().getData();
        log.debug("Data body: {}", dataBody);
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(dataBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new InternalException(ResponseCode.CANT_MAPPING_JSON_OBJECT);
        }
        String encryptData = CommonUtils.encryptAES(jsonString, secretKey);

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
            .body(new ResponseBase<>(encryptData));

    }

    public String getUserId(String subjectId) {
        log.debug("SubjectId: {}", subjectId);
        String userId = redisService.getValue(RedisKeyUtils.getUserSubjectKey(subjectId), String.class);
        if (StringUtils.isBlank(userId)) {
            User user = userService.getUserBySubjectId(subjectId);
            if (user == null) {
                log.error("User not found");
                throw new InternalException(ResponseCode.USER_NOT_FOUND);
            }
            userId = user.getId();
            redisService.setValue(RedisKeyUtils.getUserSubjectKey(subjectId), userId);
            return userId;
        }
        return userId;
    }

    /**
     * Get Admin DB Id by subject Id from redis, or else get from DB
     * @param subjectId
     * @return
     */
    public String getAdminId(String subjectId) {
        log.debug("SubjectId: {}", subjectId);
        String adminId = redisService.getValue(RedisKeyUtils.getUserSubjectKey(subjectId), String.class);
        if (StringUtils.isBlank(adminId)) {
            Administrator admin = adminService.getBySubjectId(subjectId);
            if (admin == null) {
                log.error("Administrator not found");
                throw new InternalException(ResponseCode.USER_NOT_FOUND);
            }
            adminId = admin.getId();
            redisService.setValue(RedisKeyUtils.getUserSubjectKey(subjectId), adminId);
            return adminId;
        }
        return adminId;
    }

}
