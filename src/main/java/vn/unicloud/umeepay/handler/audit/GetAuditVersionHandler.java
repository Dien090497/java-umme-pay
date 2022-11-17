package vn.unicloud.umeepay.handler.audit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.audit.request.AuditVersionRequest;
import vn.unicloud.umeepay.dtos.audit.response.AuditVersionResponse;
import vn.unicloud.umeepay.entity.ResponseMessage;
import vn.unicloud.umeepay.entity.SystemParameter;
import vn.unicloud.umeepay.service.AuditService;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAuditVersionHandler extends RequestHandler<AuditVersionRequest, AuditVersionResponse> {

    private final AuditService auditService;

    @Override
    public AuditVersionResponse handle(AuditVersionRequest request) {
        Integer version = 0;
        switch (request.getModule()) {
            case RESPONSE_MESSAGE:
                version = auditService.getCurrentVersion(ResponseMessage.class);
                break;

            case SYSTEM_PARAMETER:
                version = auditService.getCurrentVersion(SystemParameter.class);
                break;
            default:
                break;
        }

        return new AuditVersionResponse(version);
    }
}
