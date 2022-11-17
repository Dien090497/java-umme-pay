package vn.unicloud.umeepay.handler.parameter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.dtos.paramter.request.GetAllSystemParamRequest;
import vn.unicloud.umeepay.dtos.paramter.response.AllSystemParamResponse;
import vn.unicloud.umeepay.dtos.paramter.response.SystemParamResponse;
import vn.unicloud.umeepay.entity.SystemParameter;
import vn.unicloud.umeepay.repository.SystemParameterRepository;
import vn.unicloud.umeepay.service.SystemParameterService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllSystemParameterHandler extends RequestHandler<GetAllSystemParamRequest, PageResponse<SystemParamResponse>> {

    private final SystemParameterService paramService;

    @Override
    public PageResponse<SystemParamResponse> handle(GetAllSystemParamRequest request) {
        Page<SystemParameter> result = paramService.getAllParams(request, request.getPageable());
        PageResponse<SystemParamResponse> responses = new PageResponse<SystemParamResponse>()
                .setPageNumber(result.getNumber())
                .setPageSize(result.getSize())
                .setTotalPage(result.getTotalPages())
                .setTotalSize(result.getTotalElements())
                .setItems(result.getContent()
                        .stream()
                        .map(item -> new SystemParamResponse(item))
                        .collect(Collectors.toList())
                );
        return responses;
    }
}
