package vn.unicloud.umeepay.handler.message;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.message.request.GetResponseMessageRequest;
import vn.unicloud.umeepay.dtos.message.response.ResponseMessageResponse;
import vn.unicloud.umeepay.entity.ResponseMessage;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.ResponseMessageService;

@Component
@RequiredArgsConstructor
public class GetResponseMessageHandler extends RequestHandler<GetResponseMessageRequest, ResponseMessageResponse> {

    private final ResponseMessageService messageService;

    @Override
    public ResponseMessageResponse handle(GetResponseMessageRequest request) {
        ResponseMessage message = messageService.getById(request.getId());
        if(message == null) {
            throw new InternalException(ResponseCode.RESPONSE_MESSAGE_ERROR_NOT_FOUND);
        }
        return new ResponseMessageResponse(message);
    }
}
