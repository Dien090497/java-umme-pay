package vn.unicloud.umeepay.handler.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.customer.request.CreateCustomerRequest;
import vn.unicloud.umeepay.dtos.customer.request.UpdateCustomerGroupRequest;
import vn.unicloud.umeepay.dtos.customer.response.CustomerGroupResponse;
import vn.unicloud.umeepay.dtos.customer.response.CustomerResponse;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.dtos.model.CustomerGroupDto;
import vn.unicloud.umeepay.entity.Customer;
import vn.unicloud.umeepay.entity.CustomerGroup;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.CustomerGroupRepository;
import vn.unicloud.umeepay.service.MerchantService;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateCustomerGroupHandler extends RequestHandler<UpdateCustomerGroupRequest, CustomerGroupResponse> {

    private final MerchantService merchantService;

    private final CustomerGroupRepository customerGroupRepository;

    @Override
    public CustomerGroupResponse handle(UpdateCustomerGroupRequest request) {
        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());
        CustomerGroup customerGroup = customerGroupRepository.findFirstByIdAndMerchantId(request.getId(), merchant.getId());
        if (customerGroup == null) {
            log.error("Customer group invalid");
            throw new InternalException(ResponseCode.CUSTOMER_GROUP_INVALID);
        }
        customerGroup.setName(request.getName());
        customerGroup = customerGroupRepository.save(customerGroup);
        return new CustomerGroupResponse(new CustomerGroupDto(customerGroup));
    }
}
