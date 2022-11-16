package vn.unicloud.umeepay.controller;

import com.amazonaws.services.identitymanagement.model.AddUserToGroupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.ICustomerController;
import vn.unicloud.umeepay.controller.interfaces.IEkycController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.customer.request.*;
import vn.unicloud.umeepay.dtos.customer.response.CustomerGroupListResponse;
import vn.unicloud.umeepay.dtos.customer.response.CustomerGroupResponse;
import vn.unicloud.umeepay.dtos.customer.response.CustomerListResponse;
import vn.unicloud.umeepay.dtos.customer.response.CustomerResponse;
import vn.unicloud.umeepay.dtos.ekyc.request.UploadCardRequest;
import vn.unicloud.umeepay.dtos.ekyc.response.EkycResponse;
import vn.unicloud.umeepay.service.SecurityService;

@RestController
@RequiredArgsConstructor
public class CustomerController extends BaseController implements ICustomerController {

    private final SecurityService securityService;

    @Override
    public ResponseEntity<ResponseBase<CustomerResponse>> createCustomer(CreateCustomerRequest request) {
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<CustomerResponse>> updateCustomer(UpdateCustomerRequest request) {
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<CustomerListResponse>> getListCustomer() {
        GetListCustomerRequest request = new GetListCustomerRequest();
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<CustomerListResponse>> getDetailCustomer(String customerId) {
        GetCustomerRequest request = new GetCustomerRequest(customerId);
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<CustomerGroupResponse>> createCustomerGroup(CreateCustomerGroupRequest request) {
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<CustomerGroupResponse>> updateCustomerGroup(UpdateCustomerGroupRequest request) {
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<CustomerGroupListResponse>> getListCustomerGroup() {
        GetListCustomerGroupRequest request = new GetListCustomerGroupRequest();
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<CustomerGroupResponse>> getDetailCustomerGroup(String customerGroupId) {
        GetCustomerGroupRequest request = new GetCustomerGroupRequest(customerGroupId);
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<CustomerResponse>> addUserToGroup(AddCustomerToGroupRequest request) {
        request.setUserId(securityService.getUserId(getCurrentSubjectId()));
        return this.execute(request);
    }
}
