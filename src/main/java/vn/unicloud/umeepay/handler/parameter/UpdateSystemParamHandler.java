package vn.unicloud.umeepay.handler.parameter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.paramter.request.UpdateSystemParamRequest;
import vn.unicloud.umeepay.dtos.paramter.response.SystemParamResponse;
import vn.unicloud.umeepay.entity.SystemParameter;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.SystemParameterType;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.SystemParameterService;
import vn.unicloud.umeepay.utils.CommonUtils;

@Component
@RequiredArgsConstructor
public class UpdateSystemParamHandler extends RequestHandler<UpdateSystemParamRequest, SystemParamResponse> {

    private final SystemParameterService paramService;

    @Override
    public SystemParamResponse handle(UpdateSystemParamRequest request) {
        SystemParameter param = paramService.getById(request.getId());
        if (param == null) {
            throw new InternalException(ResponseCode.SYSTEM_PARAMETER_ERROR_NOT_FOUND);
        }

        param
                .setDataType(request.getDataType() != null
                        ? request.getDataType()
                        : param.getDataType())
                .setValue(request.getValue() != null
                        ? request.getValue()
                        : param.getValue())
                .setDescription(request.getDescription() != null
                        ? request.getDescription()
                        : param.getDescription());

        if (SystemParameterType.NUMBER.equals(param.getDataType()) &&
                !CommonUtils.isNumeric(param.getValue())) {
            throw new InternalException(ResponseCode.SYSTEM_PARAMETER_ERROR_INVALID_VALUE);
        }

        if (paramService.saveParam(param) != null) {
            return new SystemParamResponse(param);
        }

        throw new InternalException(ResponseCode.FAILED);
    }

}
