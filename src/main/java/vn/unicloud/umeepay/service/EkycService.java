package vn.unicloud.umeepay.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
import vn.unicloud.umeepay.client.response.EkycClientResponse;
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

    @Value("${ekyc.ocrCardPath}")
    private String EKYC_OCR_CARD_PATH;

    @Value("${ekyc.ocrIdPath}")
    private String EKYC_OCR_ID_PATH;

    private final ModelMapper modelMapper;

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

    public Ekyc save(Ekyc ekyc) {
        return ekycRepository.save(ekyc);
    }

    /**
     * Detect CMND/CCCD
     *
     * @param front
     * @param back
     * @return
     */
    public Ekyc detectIdCard(MultipartFile front, MultipartFile back) {
        if (front == null || front.isEmpty() || back == null || back.isEmpty()) {
            return null;
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add(EKYC_API_KEY_HEADER, EKYC_API_KEY);
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            String detectUrl = EKYC_ENDPOINT + EKYC_OCR_ID_PATH;
            MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
            requestEntity.add("imageFront", front.getResource());
            requestEntity.add("imageBack", back.getResource());

            Ekyc ekyc = callEkycApi(detectUrl, HttpMethod.POST, headers, requestEntity);
            return ekyc;

        } catch (Exception ex) {
            log.error("[EKYC] Detect ID card failed, {}", ex.getMessage());
        }
        return null;
    }

    /**
     * Detect PASSPORT
     *
     * @param image
     * @return
     */
    public Ekyc detectCard(MultipartFile image) {
        if (image == null || image.isEmpty()) {
            return null;
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add(EKYC_API_KEY_HEADER, EKYC_API_KEY);
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            String detectUrl = EKYC_ENDPOINT + EKYC_OCR_CARD_PATH;
            MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
            requestEntity.add("image", image.getResource());

            Ekyc ekyc = callEkycApi(detectUrl, HttpMethod.POST, headers, requestEntity);
            return ekyc;

        } catch (Exception ex) {
            log.error("[EKYC] Detect card failed, {}", ex.getMessage());
        }
        return null;
    }

    private Ekyc callEkycApi(String detectUrl,
                             HttpMethod httpMethod,
                             HttpHeaders headers,
                             Object requestEntity) {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        ResponseEntity<ResponseBase<Object>> detectResponse = restClient.callAPI(
                detectUrl,
                httpMethod,
                headers,
                requestEntity,
                (Class) ResponseBase.class);

        log.info("[EKYC-SERVICE] Detect Response {}", detectResponse.getBody());
        HashMap mapResponse = (HashMap) detectResponse.getBody().getData();
        if (mapResponse != null) {
            EkycClientResponse ekycResponse = objectMapper.convertValue(mapResponse.get("ekycIdCardEntity"), EkycClientResponse.class);
            Ekyc ekycResult = modelMapper.map(ekycResponse, Ekyc.class);
            return ekycResult;
        }
        return null;

    }


}
