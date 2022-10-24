package vn.unicloud.umeepay.client.testapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.client.RestClient;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.request.EncryptedBodyRequest;
import vn.unicloud.umeepay.dtos.response.EncryptBodyResponse;
import vn.unicloud.umeepay.entity.Credential;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.CredentialService;
import vn.unicloud.umeepay.utils.CommonUtils;
import vn.unicloud.umeepay.utils.ModelMapperUtils;

@Component
@Log4j2
public class TestApiTransaction {
    @Autowired
    private RestClient restClient;
    @Autowired
    private CredentialService credentialService;


    public <T extends BaseRequestData, U extends BaseRequestData, I extends BaseResponseData> I testTransactionClient(String url, T request, Class<U> uClass,Class<I> iClass) {
        // get secretkey
        Credential credential = credentialService.getCredentialCacheById(request.getClientId());
        if (credential == null) {
            throw new InternalException(ResponseCode.INVALID_KEY_ID);
        }

        // encrypt data
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(ModelMapperUtils.mapper(request, uClass));
            log.debug("Json string: " + jsonString);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new InternalException(ResponseCode.CANT_MAPPING_JSON_OBJECT);
        }

        String data = CommonUtils.encryptAES(jsonString, credential.getSecretKey());
        // encrypt Header
        String clientHeader = request.getClientId();
        Long timeHeader = System.currentTimeMillis();
        String validateHeader = CommonUtils.md5(request.getClientId() +
                timeHeader +
                data +
                credential.getSecretKey());

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-client", clientHeader);
        headers.set("x-api-time", String.valueOf(timeHeader));
        headers.set("x-api-validate", validateHeader);


        EncryptedBodyRequest encryptedBodyRequest = new EncryptedBodyRequest();
        encryptedBodyRequest.setData(data);

        headers.setContentType(MediaType.APPLICATION_JSON);


        // call api
        ResponseEntity<ResponseBase> response = restClient.callAPI(
                url,
                HttpMethod.POST,
                headers,
                encryptedBodyRequest,
                ResponseBase.class
        );
        log.debug("response: {}", response);


        if (response.getStatusCode() != HttpStatus.OK) {
            throw new InternalException(ResponseCode.HTTP_STATUS_FAILED);
        }
        if (response.getBody() == null) {
            throw new InternalException(ResponseCode.RESPONSE_BODY_NULL);
        }

        if (response.getBody().getData() == null) {
            throw new RuntimeException(response.getBody().getMessage());
        }

        // decrypt data
        Object o = response.getBody().getData();
        EncryptBodyResponse encryptedDataResponse = ModelMapperUtils.mapper(o,EncryptBodyResponse.class);

        String encryptedData = encryptedDataResponse.getData();

        data = CommonUtils.decryptAES(encryptedData, credential.getSecretKey());
        I testTransactionClientResponse = null;
        try {
            testTransactionClientResponse = objectMapper.readValue(data, iClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        //return
        if (testTransactionClientResponse != null) {
            return testTransactionClientResponse;
        }

        throw new InternalException(ResponseCode.FAILED);

    }


}
