package vn.unicloud.umeepay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IAuditController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.audit.request.AuditVersionRequest;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.enums.SystemModule;

@RestController
public class AuditController extends BaseController implements IAuditController {

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> getCurrentVersion(SystemModule module) {
        return this.execute(new AuditVersionRequest(module), StatusResponse.class);
    }

}
