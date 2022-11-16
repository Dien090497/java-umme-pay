package vn.unicloud.umeepay.handler.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.customer.request.GetCustomerRequest;
import vn.unicloud.umeepay.dtos.customer.request.GetListCustomerRequest;
import vn.unicloud.umeepay.dtos.customer.response.CustomerListResponse;
import vn.unicloud.umeepay.dtos.customer.response.CustomerResponse;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.entity.Customer;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.CustomerRepository;
import vn.unicloud.umeepay.service.MerchantService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class GetCustomerListHandler extends RequestHandler<GetListCustomerRequest, CustomerListResponse> {

    private final MerchantService merchantService;

    private final CustomerRepository customerRepository;

    @Override
    public CustomerListResponse handle(GetListCustomerRequest request) {
        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());
        List<Customer> customers = customerRepository.findAllByMerchantId(merchant.getId());
        return new CustomerListResponse(customers.stream().map(CustomerDto::new).collect(Collectors.toList()));
    }
}
