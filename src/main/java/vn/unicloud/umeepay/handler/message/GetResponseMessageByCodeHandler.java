package vn.unicloud.umeepay.handler.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.message.request.GetResMessageByCodeRequest;
import vn.unicloud.umeepay.dtos.message.response.ResponseMessageResponse;
import vn.unicloud.umeepay.entity.ResponseMessage;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.ResponseMessageService;

@Component
@Slf4j
@RequiredArgsConstructor
public class GetResponseMessageByCodeHandler extends RequestHandler<GetResMessageByCodeRequest, ResponseMessageResponse> {

    private final ResponseMessageService messageService;

    @Override
    public ResponseMessageResponse handle(GetResMessageByCodeRequest request) {
        ResponseMessage message = messageService.getByCode(request.getCode());
        if (message == null) {
            throw new InternalException(ResponseCode.RESPONSE_MESSAGE_ERROR_NOT_FOUND);
        }
        return new ResponseMessageResponse(message);
    }


}
