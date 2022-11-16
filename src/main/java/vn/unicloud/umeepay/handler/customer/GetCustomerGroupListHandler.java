package vn.unicloud.umeepay.handler.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.customer.request.GetCustomerGroupRequest;
import vn.unicloud.umeepay.dtos.customer.request.GetListCustomerGroupRequest;
import vn.unicloud.umeepay.dtos.customer.request.GetListCustomerRequest;
import vn.unicloud.umeepay.dtos.customer.response.CustomerGroupListResponse;
import vn.unicloud.umeepay.dtos.customer.response.CustomerListResponse;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.dtos.model.CustomerGroupDto;
import vn.unicloud.umeepay.entity.Customer;
import vn.unicloud.umeepay.entity.CustomerGroup;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.CustomerGroupRepository;
import vn.unicloud.umeepay.repository.CustomerRepository;
import vn.unicloud.umeepay.service.MerchantService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class GetCustomerGroupListHandler extends RequestHandler<GetListCustomerGroupRequest, CustomerGroupListResponse> {

    private final MerchantService merchantService;

    private final CustomerGroupRepository customerGroupRepository;

    @Override
    public CustomerGroupListResponse handle(GetListCustomerGroupRequest request) {
        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());
        List<CustomerGroup> customerGroups = customerGroupRepository.findAllByMerchantId(merchant.getId());
        return new CustomerGroupListResponse(customerGroups.stream().map(CustomerGroupDto::new).collect(Collectors.toList()));
    }
}
