package vn.unicloud.umeepay.handler.message;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.message.request.DeleteResponseMessageRequest;
import vn.unicloud.umeepay.dtos.message.response.ResponseMessageResponse;
import vn.unicloud.umeepay.entity.ResponseMessage;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.ResponseMessageService;

@Component
@RequiredArgsConstructor
public class DeleteResponseMessageHandler extends RequestHandler<DeleteResponseMessageRequest, ResponseMessageResponse> {

    private final ResponseMessageService messageService;

    @Override
    @Transactional
    public ResponseMessageResponse handle(DeleteResponseMessageRequest request) {
        ResponseMessage message = messageService.getById(request.getId());
        if (message == null) {
            throw new InternalException(ResponseCode.RESPONSE_MESSAGE_ERROR_NOT_FOUND);
        }

        if(messageService.deleteMessage(message) == null) {
            throw new InternalException(ResponseCode.FAILED);
        }

        return new ResponseMessageResponse(message);
    }
}
