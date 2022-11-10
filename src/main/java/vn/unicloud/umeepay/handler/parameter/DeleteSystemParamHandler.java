package vn.unicloud.umeepay.handler.parameter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.paramter.request.DeleteSystemParamRequest;
import vn.unicloud.umeepay.dtos.paramter.response.SystemParamResponse;
import vn.unicloud.umeepay.entity.SystemParameter;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.SystemParameterService;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteSystemParamHandler extends RequestHandler<DeleteSystemParamRequest, SystemParamResponse> {

    private final SystemParameterService paramService;

    @Override
    public SystemParamResponse handle(DeleteSystemParamRequest request) {
        SystemParameter param = paramService.getById(request.getId());
        if (param == null) {
            throw new InternalException(ResponseCode.SYSTEM_PARAMETER_ERROR_NOT_FOUND);
        }

        if (paramService.deleteParam(param) != null) {
            return new SystemParamResponse(param);
        }

        throw new InternalException(ResponseCode.FAILED);
    }
}
