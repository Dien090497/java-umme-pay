package vn.unicloud.umeepay.handler.parameter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.paramter.request.GetParamByNameRequest;
import vn.unicloud.umeepay.dtos.paramter.response.SystemParamResponse;
import vn.unicloud.umeepay.entity.SystemParameter;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.SystemParameterService;

@Component
@RequiredArgsConstructor
public class GetParamByNameHandler extends RequestHandler<GetParamByNameRequest, SystemParamResponse> {

    private final SystemParameterService parameterService;

    @Override
    public SystemParamResponse handle(GetParamByNameRequest request) {
        SystemParameter parameter = parameterService.getByName(request.getName().trim());
        if (parameter == null || !parameter.getName().equals(request.getName())) {
            throw new InternalException(ResponseCode.SYSTEM_PARAMETER_ERROR_NOT_FOUND);
        }
        return new SystemParamResponse(parameter);
    }
}
