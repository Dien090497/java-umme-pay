package vn.unicloud.umeepay.handler.paylink;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.constant.BaseConstant;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.dtos.model.CustomerInfoForm;
import vn.unicloud.umeepay.dtos.paylink.request.GetPayLinkRequest;
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

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class GetPayLinkHandler extends RequestHandler<GetPayLinkRequest, PayLinkResponse> {

    private final PayLinkRepository payLinkRepository;

    private final PayLinkService payLinkService;

    @Value("${umeepay.paylink-url-form}")
    private String payLinkFormUrl;

    @Override
    public PayLinkResponse handle(GetPayLinkRequest request) {
        PayLink payLink = payLinkService.getPayLinkByCode(request.getPayLinkCode());

        Transaction transaction = payLink.getTransaction();

        String qrCode = CommonUtils.generateQRCode(
            CommonUtils.getBinByBankType(transaction.getBankType()),
            transaction.getVirtualAccount(),
            transaction.getAmount(),
            transaction.getDescription()
        );

        PayLinkResponse response = new PayLinkResponse();
        response.setPayLinkCode(payLink.getCode());
        response.setPayLinkUrl(String.format("%s%s", payLinkFormUrl, payLink.getCode()));
        response.setQrCode(qrCode);

        Customer customer = payLink.getCustomer();
        if (customer != null) {
            response.setCustomerDto(new CustomerDto(payLink.getCustomer()));
        }
        response.setStatus(transaction.getStatus());
        response.setCustomerInfoType(payLink.getCustomerInfoType());

        CustomerInfoForm infoForm = new CustomerInfoForm();
        infoForm.setFillAddress(payLink.isShowAddress());
        infoForm.setFillEmail(payLink.isShowEmail());
        infoForm.setFillName(payLink.isShowName());
        infoForm.setFillPhone(payLink.isShowPhone());

        if (customer != null) {
            infoForm.setId(customer.getId());
        }

        response.setCustomerInfoForm(infoForm);

        return response;
    }
}
