package vn.unicloud.umeepay.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IEkycController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.ekyc.request.UploadCardRequest;
import vn.unicloud.umeepay.dtos.ekyc.response.EkycResponse;
import vn.unicloud.umeepay.service.SecurityService;

@RestController
@RequiredArgsConstructor
public class EkycController extends BaseController implements IEkycController {

    private final SecurityService securityService;

    @Override
    public ResponseEntity<ResponseBase<EkycResponse>> detectCard(UploadCardRequest request) {
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request, EkycResponse.class);
    }

}
