package vn.unicloud.umeepay.handler.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.customer.request.CreateCustomerGroupRequest;
import vn.unicloud.umeepay.dtos.customer.request.CreateCustomerRequest;
import vn.unicloud.umeepay.dtos.customer.response.CustomerGroupResponse;
import vn.unicloud.umeepay.dtos.customer.response.CustomerResponse;
import vn.unicloud.umeepay.dtos.model.CustomerGroupDto;
import vn.unicloud.umeepay.entity.CustomerGroup;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.CustomerGroupRepository;
import vn.unicloud.umeepay.service.MerchantService;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateCustomerGroupHandler extends RequestHandler<CreateCustomerGroupRequest, CustomerGroupResponse> {

    private final MerchantService merchantService;

    private final CustomerGroupRepository customerGroupRepository;

    @Override
    public CustomerGroupResponse handle(CreateCustomerGroupRequest request) {
        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());
        CustomerGroup customerGroup = new CustomerGroup();
        customerGroup.setName(request.getName());
        customerGroup.setMerchant(merchant);

        customerGroup = customerGroupRepository.save(customerGroup);

        return new CustomerGroupResponse(new CustomerGroupDto(customerGroup));
    }
}
