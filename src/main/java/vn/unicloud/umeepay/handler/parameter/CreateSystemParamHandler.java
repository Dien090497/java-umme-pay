package vn.unicloud.umeepay.handler.parameter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.paramter.request.CreateSystemParamRequest;
import vn.unicloud.umeepay.dtos.paramter.response.SystemParamResponse;
import vn.unicloud.umeepay.entity.SystemParameter;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.SystemParameterType;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.SystemParameterService;
import vn.unicloud.umeepay.utils.CommonUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateSystemParamHandler extends RequestHandler<CreateSystemParamRequest, SystemParamResponse> {

    private final SystemParameterService paramService;

    @Override
    public SystemParamResponse handle(CreateSystemParamRequest request) {
        if (paramService.getByName(request.getName()) != null) {
            throw new InternalException(ResponseCode.SYSTEM_PARAMETER_ERROR_EXISTED_NAME);
        }

        SystemParameter parameter = new SystemParameter()
                .setName(request.getName())
                .setGroup(request.getGroup())
                .setValue(request.getValue())
                .setDataType(request.getDataType())
                .setDescription(request.getDescription());

        if (SystemParameterType.NUMBER.equals(parameter.getDataType()) &&
                !CommonUtils.isNumeric(parameter.getValue())) {
            throw new InternalException(ResponseCode.SYSTEM_PARAMETER_ERROR_INVALID_VALUE);
        }

        if (paramService.saveParam(parameter) != null) {
            return new SystemParamResponse(parameter);
        }

        throw new InternalException(ResponseCode.FAILED);
    }
}
