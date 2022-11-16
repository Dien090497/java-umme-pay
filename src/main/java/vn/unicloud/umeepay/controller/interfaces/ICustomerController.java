package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.umeepay.config.OpenApiConfig;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.customer.request.*;
import vn.unicloud.umeepay.dtos.customer.response.CustomerGroupListResponse;
import vn.unicloud.umeepay.dtos.customer.response.CustomerGroupResponse;
import vn.unicloud.umeepay.dtos.customer.response.CustomerListResponse;
import vn.unicloud.umeepay.dtos.customer.response.CustomerResponse;

import javax.validation.Valid;


@Tag(name = "Customer Controller", description = "Thao tác với customer")
@RequestMapping(value = "/api/customer")
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public interface ICustomerController {

    @Operation(summary = "Tạo customer")
    @PostMapping(value = "/v1/create")
    ResponseEntity<ResponseBase<CustomerResponse>> createCustomer(@RequestBody @Valid CreateCustomerRequest request);

    @Operation(summary = "Cập nhật customer")
    @PutMapping(value = "/v1/update")
    ResponseEntity<ResponseBase<CustomerResponse>> updateCustomer(@RequestBody @Valid UpdateCustomerRequest request);

    @Operation(summary = "Lấy danh sách customer")
    @GetMapping(value = "/v1/getList")
    ResponseEntity<ResponseBase<CustomerListResponse>> getListCustomer();

    @Operation(summary = "Lấy chi tiết customer")
    @GetMapping(value = "/v1/getDetail/{customerId}")
    ResponseEntity<ResponseBase<CustomerListResponse>> getDetailCustomer(@PathVariable String customerId);

    @Operation(summary = "Tạo customer group")
    @PostMapping(value = "/v1/createGroup")
    ResponseEntity<ResponseBase<CustomerGroupResponse>> createCustomerGroup(@RequestBody @Valid CreateCustomerGroupRequest request);

    @Operation(summary = "Cập nhật customer group")
    @PutMapping(value = "/v1/updateGroup")
    ResponseEntity<ResponseBase<CustomerGroupResponse>> updateCustomerGroup(@RequestBody @Valid UpdateCustomerGroupRequest request);

    @Operation(summary = "Lấy danh sách customer group")
    @GetMapping(value = "/v1/getGroupList")
    ResponseEntity<ResponseBase<CustomerGroupListResponse>> getListCustomerGroup();

    @Operation(summary = "Lấy chi tiết customer group")
    @GetMapping(value = "/v1/getGroupDetail/{customerGroupId}")
    ResponseEntity<ResponseBase<CustomerGroupResponse>> getDetailCustomerGroup(@PathVariable String customerGroupId);

    @Operation(summary = "Lấy chi tiết customer group")
    @PutMapping(value = "/v1/addCustomerToGroup")
    ResponseEntity<ResponseBase<CustomerResponse>> addUserToGroup(@RequestBody @Valid AddCustomerToGroupRequest request);

}
