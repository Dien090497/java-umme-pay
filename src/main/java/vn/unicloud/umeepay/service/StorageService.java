package vn.unicloud.umeepay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import vn.unicloud.umeepay.client.RestClient;
import vn.unicloud.umeepay.client.response.UploadFileClientResponse;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {

    @Value("${storage.endpoint}")
    private String STORAGE_ENDPOINT;

    @Value("${storage.bucket}")
    private String STORAGE_BUCKET;

    @Value("${storage.path}")
    private String STORAGE_PATH;

    private final RedisService redisService;

    private final RestClient restClient;

    private final ObjectMapper objectMapper;

    public InputStream downloadFile(String objectName) {
        if (objectName == null) {
            return null;
        }
        return null;
    }

    public String uploadFile(MultipartFile file) {
        if (file == null) {
            return null;
        }

        log.error("Upload file to storage: {}", file.getOriginalFilename());
        String uploadFileUrl = STORAGE_ENDPOINT + "/static/v1";
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-API-KEY", "KEY");
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("bucket", STORAGE_BUCKET);
        requestEntity.add("path", STORAGE_PATH);
        requestEntity.add("fileName", file.getOriginalFilename());
        requestEntity.add("mediaType", file.getContentType());
        requestEntity.add("file", file.getResource());

        try {
            ResponseEntity<ResponseBase<Object>> response =
                    restClient.callAPI(uploadFileUrl, HttpMethod.POST, headers, requestEntity, (Class) ResponseBase.class);

            if (response != null &&
                    response.getBody() != null &&
                    response.getBody().getCode() == 0 &&
                    response.getBody().getData() != null) {
                UploadFileClientResponse dataResponse = objectMapper.convertValue(response.getBody().getData(), UploadFileClientResponse.class);
                return dataResponse.getPath();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Upload file to storage failed {}", ex.getMessage());
        }
        throw new InternalException(ResponseCode.FAILED.STORAGE_ERROR_UPLOAD_FAILED);
    }

}
