package vn.unicloud.vietqr.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class BaseController {

    @Autowired
    protected SpringBus springBus;

    protected <T extends RequestData, I extends ResponseData> ResponseEntity<ResponseBase<I>> execute(T request, Class<I> response) {
        return ResponseEntity.ok(new ResponseBase<>(this.springBus.execute(request, response)));
    }

}
