package vn.unicloud.umeepay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IResponseMessageController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.message.request.*;
import vn.unicloud.umeepay.dtos.message.response.ListResponseMessageResponse;
import vn.unicloud.umeepay.dtos.message.response.ResponseMessageResponse;

@RestController
public class ResponseMessageController extends BaseController implements IResponseMessageController {

    @Override
    public ResponseEntity<ResponseBase<ListResponseMessageResponse>> getAllResponseMessages() {
        return this.execute(new GetListResponseMessageRequest());
    }

    @Override
    public ResponseEntity<ResponseBase<ResponseMessageResponse>> createResponseMessage(CreateResponseMessageRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<ResponseMessageResponse>> getDetailResponseMessage(Long id) {
        return this.execute(new GetResponseMessageRequest(id));
    }

    @Override
    public ResponseEntity<ResponseBase<ResponseMessageResponse>> getDetailResponseMessage(Integer code) {
        return this.execute(new GetResMessageByCodeRequest(code));
    }

    @Override
    public ResponseEntity<ResponseBase<ResponseMessageResponse>> deleteResponseMessage(Long id) {
        return this.execute(new DeleteResponseMessageRequest(id));
    }

    @Override
    public ResponseEntity<ResponseBase<ResponseMessageResponse>> updateResponseMessage(Long id, UpdateResponseMessageRequest request) {
        request.setId(id);
        return this.execute(request);
    }


}
