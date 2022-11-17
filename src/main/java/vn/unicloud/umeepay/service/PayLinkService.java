package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.client.ThirdPartyClient;
import vn.unicloud.umeepay.client.request.NotifyRequest;
import vn.unicloud.umeepay.dtos.paygate.request.DepositCheckingRequest;
import vn.unicloud.umeepay.dtos.paygate.request.InquiryCheckingRequest;
import vn.unicloud.umeepay.dtos.paygate.request.NotifyTransactionRequest;
import vn.unicloud.umeepay.dtos.paygate.response.DepositCheckingResponse;
import vn.unicloud.umeepay.dtos.paygate.response.InquiryCheckingResponse;
import vn.unicloud.umeepay.dtos.paygate.response.NotifyTransactionResponse;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.PayLink;
import vn.unicloud.umeepay.entity.Transaction;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.TransactionStatus;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.PayLinkRepository;
import vn.unicloud.umeepay.repository.TransactionRepository;
import vn.unicloud.umeepay.utils.CommonUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Log4j2
@RequiredArgsConstructor
public class PayLinkService {

    private final PayLinkRepository payLinkRepository;

    public PayLink getPayLinkByCode(String payLinkCode) {
        if (payLinkCode == null) {
            throw new InternalException(ResponseCode.PAYLINK_INVALID);
        }
        PayLink payLink = payLinkRepository.findFirstByCode(payLinkCode);
        if (payLink == null) {
            throw new InternalException(ResponseCode.PAYLINK_NOT_FOUND);
        }
        return payLink;
    }

    public PayLink save(PayLink payLink) {
        return payLinkRepository.save(payLink);
    }

}
