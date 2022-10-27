package vn.unicloud.umeepay.client;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.client.request.NotifyRequest;
import vn.unicloud.umeepay.client.response.NotifyResponse;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;

@Component
@Log4j2
@RequiredArgsConstructor
public class ThirdPartyClient {

    private final RestClient restClient;

    @SneakyThrows
    public NotifyResponse notify(String url, String apiKey, NotifyRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", apiKey);
        ResponseEntity<NotifyResponse> response = restClient.callAPI(
            url,
            HttpMethod.POST,
            headers,
            request,
            NotifyResponse.class
        );
        log.debug("response: {}", response);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new InternalException(ResponseCode.CALL_WEBHOOK_ERROR);
        }
        if (response.getBody() != null) {
            return response.getBody();
        }
        throw new InternalException(ResponseCode.CALL_WEBHOOK_ERROR);
    }

}
