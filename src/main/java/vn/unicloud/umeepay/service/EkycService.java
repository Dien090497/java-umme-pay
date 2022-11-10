package vn.unicloud.umeepay.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
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
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.entity.Ekyc;
import vn.unicloud.umeepay.repository.EkycRepository;

import java.util.HashMap;


@Service
@RequiredArgsConstructor
@Slf4j
public class EkycService {

    private final RestClient restClient;

    @Value("${ekyc.endpoint}")
    private String EKYC_ENDPOINT;

    @Value("${ekyc.apiKeyHeader}")
    private String EKYC_API_KEY_HEADER;

    @Value("${ekyc.apiKey}")
    private String EKYC_API_KEY;

    ObjectMapper objectMapper = new ObjectMapper();


    private final EkycRepository ekycRepository;

    /**
     * Get Ekyc in DB by ID
     *
     * @param id
     * @return
     */
    public Ekyc getById(Long id) {
        return id != null
                ? ekycRepository.findById(id).orElse(null)
                : null;
    }

    /**
     * Get save Ekyc into
     *
     * @param ekyc
     * @return
     */
    public Ekyc save(Ekyc ekyc) {
        return ekycRepository.save(ekyc);
    }

    public Ekyc detectCard(MultipartFile front, MultipartFile back) {
        if (front == null || front.isEmpty()) {
            return null;
        }

        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            HttpHeaders headers = new HttpHeaders();
            headers.add(EKYC_API_KEY_HEADER, EKYC_API_KEY);
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            String detectUrl = null;
            MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();

            if (back != null && !back.isEmpty()) {
                detectUrl = EKYC_ENDPOINT + "/api/ekyc/v1/detect-card/sides";
                requestEntity.add("imageFront", front.getResource());
                requestEntity.add("imageBack", front.getResource());
            } else {
                detectUrl = EKYC_ENDPOINT + "/api/ekyc/v1/idcard/front";
                requestEntity.add("image", front.getResource());
            }

            ResponseEntity<ResponseBase<Object>> detectResponse = restClient.callAPI(
                    detectUrl,
                    HttpMethod.POST,
                    headers,
                    requestEntity,
                    (Class) ResponseBase.class);

            HashMap mapResponse = (HashMap) detectResponse.getBody().getData();
            log.info("[EKYC-SERVICE] Detect Response {}", mapResponse);
            if (mapResponse != null) {
                Ekyc result = objectMapper.convertValue(mapResponse.get("ekycIdCardEntity"), Ekyc.class);
                return result;
            }
        } catch (Exception ex) {
            log.error("[EKYC] Detect card failed, {}", ex.getMessage());
        }
        return null;
    }


}
