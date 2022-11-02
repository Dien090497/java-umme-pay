package vn.unicloud.umeepay.controller.interfaces;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.paramter.request.CreateSystemParamRequest;
import vn.unicloud.umeepay.dtos.paramter.request.DeleteSystemParamRequest;
import vn.unicloud.umeepay.dtos.paramter.request.GetAllSystemParamRequest;
import vn.unicloud.umeepay.dtos.paramter.request.UpdateSystemParamRequest;
import vn.unicloud.umeepay.dtos.paramter.response.AllSystemParamResponse;
import vn.unicloud.umeepay.dtos.paramter.response.SystemParamResponse;
import vn.unicloud.umeepay.enums.SystemParameterGroup;
import vn.unicloud.umeepay.enums.SystemParameterType;

@RestController
public class SystemParamController extends BaseController implements ISystemParamController {

    @Override
    public ResponseEntity<ResponseBase<AllSystemParamResponse>> getAllParameters(
            SystemParameterGroup group,
            SystemParameterType dataType,
            Sort.Direction sortDirection,
            String sortBy) {

        GetAllSystemParamRequest request = new GetAllSystemParamRequest()
                .setDataType(dataType)
                .setGroup(group)
                .setSort(Sort.by(sortDirection, sortBy));

        return this.execute(request, AllSystemParamResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<SystemParamResponse>> updateParameter(Long id, UpdateSystemParamRequest request) {
        request.setId(id);
        return this.execute(request, SystemParamResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<SystemParamResponse>> createParameter(CreateSystemParamRequest request) {
        return this.execute(request, SystemParamResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<SystemParamResponse>> deleteParameter(Long id) {
        return this.execute(new DeleteSystemParamRequest(id), SystemParamResponse.class);
    }

}
