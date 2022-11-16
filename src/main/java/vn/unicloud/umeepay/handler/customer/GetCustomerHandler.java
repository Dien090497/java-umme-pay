package vn.unicloud.umeepay.handler.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.customer.request.CreateCustomerRequest;
import vn.unicloud.umeepay.dtos.customer.request.GetCustomerRequest;
import vn.unicloud.umeepay.dtos.customer.response.CustomerGroupResponse;
import vn.unicloud.umeepay.dtos.customer.response.CustomerResponse;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.dtos.model.CustomerGroupDto;
import vn.unicloud.umeepay.entity.Customer;
import vn.unicloud.umeepay.entity.CustomerGroup;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.CustomerRepository;
import vn.unicloud.umeepay.service.MerchantService;

@Component
@Slf4j
@RequiredArgsConstructor
public class GetCustomerHandler extends RequestHandler<GetCustomerRequest, CustomerResponse> {

    private final MerchantService merchantService;

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse handle(GetCustomerRequest request) {
        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());
        Customer customer = customerRepository.findFirstByIdAndMerchantId(request.getId(), merchant.getId());
        if (customer == null) {
            log.error("Customer invalid");
            throw new InternalException(ResponseCode.CUSTOMER_INVALID);
        }
        return new CustomerResponse(new CustomerDto(customer));
    }
}
