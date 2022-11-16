package vn.unicloud.umeepay.core;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;

public class BaseController {

    @Autowired
    protected SpringBus springBus;

    protected <T extends RequestData, I extends ResponseData> ResponseEntity<ResponseBase<I>> execute(T request) {
        return ResponseEntity.ok(new ResponseBase<>(this.springBus.execute(request)));
    }

    protected String getCurrentSubjectId() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null ||
            securityContext.getAuthentication() == null ||
            StringUtils.isBlank(securityContext.getAuthentication().getName())) {
            throw new InternalException(ResponseCode.AUTHORIZATION_FAILED);
        }
        return securityContext.getAuthentication().getName();
    }
}
