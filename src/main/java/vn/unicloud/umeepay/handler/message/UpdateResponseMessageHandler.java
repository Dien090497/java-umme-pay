package vn.unicloud.umeepay.handler.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.message.request.UpdateResponseMessageRequest;
import vn.unicloud.umeepay.dtos.message.response.ResponseMessageResponse;
import vn.unicloud.umeepay.entity.ResponseMessage;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.ResponseMessageService;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateResponseMessageHandler extends RequestHandler<UpdateResponseMessageRequest, ResponseMessageResponse> {

    private final ResponseMessageService messageService;

    @Override
    @Transactional
    public ResponseMessageResponse handle(UpdateResponseMessageRequest request) {
        ResponseMessage message = messageService.getById(request.getId());
        if (message == null) {
            throw new InternalException(ResponseCode.RESPONSE_MESSAGE_ERROR_NOT_FOUND);
        }

        // validate code
        if (request.getCode() != null &&
                request.getCode() != message.getCode() &&
                messageService.getByCode(request.getCode()) != null) {
            throw new InternalException(ResponseCode.RESPONSE_MESSAGE_ERROR_EXISTED_CODE);
        }

        message
                .setCode(request.getCode() != null
                        ? request.getCode()
                        : message.getCode())
                .setViContent(request.getViContent() != null
                        ? request.getViContent()
                        : message.getViContent())
                .setEnContent(request.getEnContent() != null
                        ? request.getEnContent()
                        : message.getEnContent())
                .setDescription(request.getDescription() != null
                        ? request.getDescription()
                        : message.getDescription());

        if (messageService.saveMessage(message) != null) {
            return new ResponseMessageResponse(message);
        }

        throw new InternalException(ResponseCode.FAILED);
    }
}
