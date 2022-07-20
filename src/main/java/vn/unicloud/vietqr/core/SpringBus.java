package vn.unicloud.vietqr.core;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vn.unicloud.vietqr.enums.ResponseCode;
import vn.unicloud.vietqr.exception.InternalException;

@Component
@Log4j2
public class SpringBus implements CqrsBus {
    private final Registry registry;

    public <T extends RequestData, I extends ResponseData> I execute(T requestData, Class<I> responseClass) {
        Handler handler = this.getHandler(requestData);
        if (handler == null || handler instanceof UnsupportedRequestHandler) {
            throw new InternalException(ResponseCode.UNHANDLE_REQUEST);
        }
        return (I) handler.handle(requestData);
    }

    private <T extends RequestData> Handler getHandler(T requestData) {
        if (requestData instanceof BaseRequestData) {
            return this.registry.getCommandHandler(((BaseRequestData)requestData).getClass());
        } else {
            return null;
        }
    }

    public SpringBus(Registry registry) {
        this.registry = registry;
    }
}

