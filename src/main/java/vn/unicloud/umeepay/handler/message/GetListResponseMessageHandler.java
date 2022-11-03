package vn.unicloud.umeepay.handler.message;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.message.request.GetListResponseMessageRequest;
import vn.unicloud.umeepay.dtos.message.response.ListResponseMessageResponse;
import vn.unicloud.umeepay.dtos.message.response.ResponseMessageResponse;
import vn.unicloud.umeepay.entity.ResponseMessage;
import vn.unicloud.umeepay.service.ResponseMessageService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetListResponseMessageHandler extends RequestHandler<GetListResponseMessageRequest, ListResponseMessageResponse> {

    private final ResponseMessageService messageService;

    @Override
    public ListResponseMessageResponse handle(GetListResponseMessageRequest request) {
        List<ResponseMessage> messages = messageService.getAll();
        return new ListResponseMessageResponse(
                messages
                        .stream()
                        .map(mess -> new ResponseMessageResponse(mess))
                        .collect(Collectors.toList())
        );
    }
}
