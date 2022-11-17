package vn.unicloud.umeepay.handler.paylink;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.constant.BaseConstant;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.dtos.model.CustomerFillDto;
import vn.unicloud.umeepay.dtos.model.CustomerInfoForm;
import vn.unicloud.umeepay.dtos.paylink.request.CreatePayLinkRequest;
import vn.unicloud.umeepay.dtos.paylink.request.UpdateCustomerInfoRequest;
import vn.unicloud.umeepay.dtos.paylink.response.PayLinkResponse;
import vn.unicloud.umeepay.entity.*;
import vn.unicloud.umeepay.enums.CustomerInfoType;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.TransactionStatus;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.BankAccountRepository;
import vn.unicloud.umeepay.repository.CustomerRepository;
import vn.unicloud.umeepay.repository.PayLinkRepository;
import vn.unicloud.umeepay.service.MerchantService;
import vn.unicloud.umeepay.service.PayLinkService;
import vn.unicloud.umeepay.utils.CommonUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateCustomerInfoHandler extends RequestHandler<UpdateCustomerInfoRequest, StatusResponse> {

    private final PayLinkService payLinkService;

    @Override
    public StatusResponse handle(UpdateCustomerInfoRequest request) {
        PayLink payLink = payLinkService.getPayLinkByCode(request.getPayLinkCode());

        if (!payLink.getCustomerInfoType().equals(CustomerInfoType.REQUIRE_INPUT)) {
            log.error("Not allow to update info");
            throw new InternalException(ResponseCode.CUSTOMER_UPDATE_NOT_ALLOW);
        }

        CustomerFillDto customerFill = request.getCustomer();

        Customer customer = payLink.getCustomer();
        if (customer == null) {
            customer = new Customer();
        }
        customer.setPhone(customerFill.getPhone());
        customer.setEmail(customerFill.getEmail());
        customer.setName(customerFill.getFullName());
        customer.setAddress(customerFill.getAddress());

        payLink.setCustomer(customer);

        payLinkService.save(payLink);

        return new StatusResponse(true);
    }
}
