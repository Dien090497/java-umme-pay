package vn.unicloud.umeepay.handler.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.message.request.CreateResponseMessageRequest;
import vn.unicloud.umeepay.dtos.message.response.ResponseMessageResponse;
import vn.unicloud.umeepay.entity.ResponseMessage;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.ResponseMessageService;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateResponseMessageHandler extends RequestHandler<CreateResponseMessageRequest, ResponseMessageResponse> {

    private final ResponseMessageService messageService;

    @Override
    @Transactional
    public ResponseMessageResponse handle(CreateResponseMessageRequest request) {
        if (messageService.getByCode(request.getCode()) != null) {
            throw new InternalException(ResponseCode.RESPONSE_MESSAGE_ERROR_EXISTED_CODE);
        }

        ResponseMessage message = new ResponseMessage()
                .setCode(request.getCode())
                .setViContent(request.getViContent())
                .setEnContent(request.getEnContent())
                .setDescription(request.getDescription());

        ResponseMessage savedMessage = messageService.saveMessage(message);
        if (savedMessage != null) {
            return new ResponseMessageResponse(savedMessage);
        }

        throw new InternalException(ResponseCode.FAILED);
    }
}
