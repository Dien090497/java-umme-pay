package vn.unicloud.umeepay.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.constant.BaseConstant;
import vn.unicloud.umeepay.controller.interfaces.ISystemParamController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.dtos.paramter.request.*;
import vn.unicloud.umeepay.dtos.paramter.response.SystemParamResponse;
import vn.unicloud.umeepay.enums.SystemParameterGroup;
import vn.unicloud.umeepay.enums.SystemParameterType;

@RestController
public class SystemParamController extends BaseController implements ISystemParamController {

    @Override
    public ResponseEntity<ResponseBase<PageResponse<SystemParamResponse>>> getAllParameters(
            SystemParameterGroup group,
            SystemParameterType dataType,
            String name,
            String value,
            Integer page,
            Integer pageSize,
            Sort.Direction sortDirection,
            String sortBy) {

        GetAllSystemParamRequest request = new GetAllSystemParamRequest()
                .setDataType(dataType)
                .setGroup(group)
                .setValue(value)
                .setName(name)
                .setPageable(PageRequest.of(page, pageSize, Sort.by(sortDirection, sortBy)));

        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<SystemParamResponse>> getParameterByName(String name) {
        return this.execute(new GetParamByNameRequest(name));
    }

    @Override
    public ResponseEntity<ResponseBase<SystemParamResponse>> updateParameter(Long id, UpdateSystemParamRequest request) {
        request.setId(id);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<SystemParamResponse>> createParameter(CreateSystemParamRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<SystemParamResponse>> deleteParameter(Long id) {
        return this.execute(new DeleteSystemParamRequest(id));
    }

}
