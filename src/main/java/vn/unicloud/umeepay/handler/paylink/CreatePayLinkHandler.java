package vn.unicloud.umeepay.handler.paylink;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.constant.BaseConstant;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.model.CustomerDto;
import vn.unicloud.umeepay.dtos.model.CustomerInfoForm;
import vn.unicloud.umeepay.dtos.paylink.request.CreatePayLinkRequest;
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
import vn.unicloud.umeepay.utils.CommonUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreatePayLinkHandler extends RequestHandler<CreatePayLinkRequest, PayLinkResponse> {

    private final MerchantService merchantService;

    private final PayLinkRepository payLinkRepository;

    private final BankAccountRepository bankAccountRepository;

    private final CustomerRepository customerRepository;

    @Value("${umeepay.prefix}")
    private String prefix;

    @Value("${umeepay.paylink-url-form}")
    private String payLinkFormUrl;

    @Value("${umeepay.default-redirect-after:5}")
    private int defaultRedirectAfter;

    @Override
    public PayLinkResponse handle(CreatePayLinkRequest request) {
        Merchant merchant = merchantService.getMerchantByUserId(request.getUserId());
        if (request.getCustomerInfoForm() == null &&
            (request.getCustomerInfoType().equals(CustomerInfoType.AUTO_FILL) ||
                request.getCustomerInfoType().equals(CustomerInfoType.REQUIRE_INPUT))) {
            throw new InternalException(ResponseCode.CUSTOMER_INVALID);
        }
        Customer customer = null;
        if (request.getCustomerInfoType().equals(CustomerInfoType.AUTO_FILL)) {
            if (request.getCustomerInfoForm() == null || request.getCustomerInfoForm().getId() == null) {
                log.error("Customer invalid");
                throw new InternalException(ResponseCode.CUSTOMER_INVALID);
            }
            CustomerInfoForm customerInfo = request.getCustomerInfoForm();
            customer = customerRepository.findFirstByIdAndMerchantId(customerInfo.getId(), merchant.getId());
            if (customer == null) {
                throw new InternalException(ResponseCode.CUSTOMER_INVALID);
            }
        }
        BankAccount bankAccount = bankAccountRepository.findById(request.getBankAccountId()).orElseThrow(
            () -> {throw new InternalException(ResponseCode.BANK_ACCOUNT_NOT_FOUND);}
        );
        String payLinkCode = CommonUtils.getRandomString(BaseConstant.PAYLINK_CODE_SIZE, false);

        Transaction transaction = Transaction.builder()
            .amount(request.getAmount())
            .refTransactionId(request.getRefTransactionId())
            .bankType(bankAccount.getBankType())
            .status(TransactionStatus.CREATED)
            .accountNo(bankAccount.getAccountNumber())
            .accountName(bankAccount.getAccountName())
            .virtualAccount(CommonUtils.generateVirtualAccount(prefix))
            .timeout(CommonUtils.getTimeoutInSec(request.getExpireType()))
            .timestamp(Instant.now().getEpochSecond())
            .createDateTime(LocalDateTime.now())
            .description("TT " + payLinkCode)
            .successUrl(request.getSuccessUrl())
            .failUrl(request.getFailUrl())
            .callbackAfter(Objects.requireNonNullElse(request.getRedirectAfter(), defaultRedirectAfter))
            .merchant(merchant)
            .build();

        PayLink payLink = PayLink.builder()
            .code(payLinkCode)
            .transaction(transaction)
            .expireType(request.getExpireType())
            .description(request.getDescription())
            .customerInfoType(request.getCustomerInfoType())
            .merchant(merchant)
            .fileUrl(request.getFileUrl())
            .build();

        if (customer != null) {
            payLink.setCustomer(customer);
        }

        if (request.getCustomerInfoForm() != null) {
            CustomerInfoForm form = request.getCustomerInfoForm();
            payLink.setShowAddress(form.isFillAddress());
            payLink.setShowEmail(form.isFillEmail());
            payLink.setShowPhone(form.isFillPhone());
            payLink.setShowName(form.isFillName());
        }

        payLink = payLinkRepository.save(payLink);

        String payLinkUrl = String.format("%s%s", payLinkFormUrl, payLink.getCode());

        String qrCode = CommonUtils.generateQRCode(
            CommonUtils.getBinByBankType(bankAccount.getBankType()),
            transaction.getVirtualAccount(),
            transaction.getAmount(),
            transaction.getDescription()
        );

        CustomerDto customerDto = null;
        if (customer != null) {
            customerDto = new CustomerDto(customer);
        }

        return new PayLinkResponse(
            payLinkCode,
            payLinkUrl,
            qrCode,
            customerDto,
            transaction.getStatus()
        );

    }
}
