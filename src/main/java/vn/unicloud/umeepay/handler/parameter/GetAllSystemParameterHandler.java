package vn.unicloud.umeepay.handler.parameter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.paramter.request.GetAllSystemParamRequest;
import vn.unicloud.umeepay.dtos.paramter.response.AllSystemParamResponse;
import vn.unicloud.umeepay.dtos.paramter.response.SystemParamResponse;
import vn.unicloud.umeepay.entity.SystemParameter;
import vn.unicloud.umeepay.service.SystemParameterService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllSystemParameterHandler extends RequestHandler<GetAllSystemParamRequest, AllSystemParamResponse> {

    private final SystemParameterService paramService;

    @Override
    public AllSystemParamResponse handle(GetAllSystemParamRequest request) {
        List<SystemParameter> params = paramService.getAllParam(request, request.getSort());
        return new AllSystemParamResponse(
                params
                        .stream()
                        .map(param -> new SystemParamResponse(param))
                        .collect(Collectors.toList())
        );
    }
}
