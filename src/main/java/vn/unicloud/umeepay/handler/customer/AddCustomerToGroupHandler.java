package vn.unicloud.umeepay.handler.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.customer.request.AddCustomerToGroupRequest;
import vn.unicloud.umeepay.dtos.customer.request.CreateCustomerRequest;
import vn.unicloud.umeepay.dtos.customer.response.CustomerResponse;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.entity.Customer;
import vn.unicloud.umeepay.entity.CustomerGroup;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.CustomerGroupRepository;
import vn.unicloud.umeepay.repository.CustomerRepository;
import vn.unicloud.umeepay.service.MerchantService;

@Component
@Slf4j
@RequiredArgsConstructor
public class AddCustomerToGroupHandler extends RequestHandler<AddCustomerToGroupRequest, CustomerResponse> {

    private final MerchantService merchantService;

    private final CustomerRepository customerRepository;

    private final CustomerGroupRepository customerGroupRepository;

    @Override
    public CustomerResponse handle(AddCustomerToGroupRequest request) {
        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());
        Customer customer = customerRepository.findFirstByIdAndMerchantId(request.getCustomerId(), merchant.getId());
        if (customer == null) {
            throw new InternalException(ResponseCode.CUSTOMER_INVALID);
        }
        CustomerGroup customerGroup = customerGroupRepository.findFirstByIdAndMerchantId(request.getGroupId(), merchant.getId());
        if (customerGroup == null) {
            throw new InternalException(ResponseCode.CUSTOMER_GROUP_INVALID);
        }
        customer.setGroupCustomer(customerGroup);
        customer = customerRepository.save(customer);
        return new CustomerResponse(new CustomerDto(customer));
    }
}
