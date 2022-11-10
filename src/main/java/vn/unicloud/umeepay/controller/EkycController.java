package vn.unicloud.umeepay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IEkycController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.ekyc.request.DetectCardRequest;
import vn.unicloud.umeepay.dtos.ekyc.response.EkycResponse;

@RestController
public class EkycController extends BaseController implements IEkycController {

    @Override
    public ResponseEntity<ResponseBase<EkycResponse>> detectCard(DetectCardRequest request) {
        return this.execute(request, EkycResponse.class);
    }

}
