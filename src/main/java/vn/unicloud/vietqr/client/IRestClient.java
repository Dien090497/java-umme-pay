package vn.unicloud.vietqr.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface IRestClient {
    <T> ResponseEntity<T> callAPI(
            String url,
            HttpMethod httpMethod,
            HttpHeaders httpHeaders,
            Object request,
            Class<T> responseClassType
    );
}
