package vn.unicloud.umeepay.handler.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.customer.request.CreateCustomerRequest;
import vn.unicloud.umeepay.dtos.customer.response.CustomerResponse;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.dtos.user.request.ChangePasswordRequest;
import vn.unicloud.umeepay.entity.Customer;
import vn.unicloud.umeepay.entity.CustomerGroup;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.User;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.model.ChangePasswordCache;
import vn.unicloud.umeepay.repository.CustomerGroupRepository;
import vn.unicloud.umeepay.repository.CustomerRepository;
import vn.unicloud.umeepay.service.KeycloakService;
import vn.unicloud.umeepay.service.MerchantService;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.service.UserService;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateCustomerHandler extends RequestHandler<CreateCustomerRequest, CustomerResponse> {

    private final CustomerRepository customerRepository;

    private final CustomerGroupRepository customerGroupRepository;

    private final MerchantService merchantService;

    @Override
    public CustomerResponse handle(CreateCustomerRequest request) {
        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());
        Customer customer = new Customer();
        customer.setAddress(request.getAddress());
        customer.setEmail(request.getEmail());
        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setMerchant(merchant);

        if (StringUtils.isNotBlank(request.getGroupId())) {
            CustomerGroup customerGroup = customerGroupRepository.findById(request.getGroupId()).orElse(null);
            customer.setGroupCustomer(customerGroup);
        }

        customer = customerRepository.save(customer);

        return new CustomerResponse(new CustomerDto(customer));
    }
}
