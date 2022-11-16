package vn.unicloud.umeepay.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.client.testapi.paygate.request.DepositCheckingClientRequest;
import vn.unicloud.umeepay.client.testapi.paygate.request.InquiryCheckingClientRequest;
import vn.unicloud.umeepay.client.testapi.paygate.request.NotifyTransactionClientRequest;
import vn.unicloud.umeepay.client.testapi.paygate.response.DepositCheckingClientResponse;
import vn.unicloud.umeepay.client.testapi.paygate.response.InquiryCheckingClientResponse;
import vn.unicloud.umeepay.client.testapi.paygate.response.NotifyTransactionClientResponse;
import vn.unicloud.umeepay.controller.interfaces.IPaygateController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.paygate.request.DepositCheckingRequest;
import vn.unicloud.umeepay.dtos.paygate.request.InquiryCheckingRequest;
import vn.unicloud.umeepay.dtos.paygate.request.NotifyTransactionRequest;
import vn.unicloud.umeepay.dtos.paygate.response.DepositCheckingResponse;
import vn.unicloud.umeepay.dtos.paygate.response.InquiryCheckingResponse;
import vn.unicloud.umeepay.dtos.paygate.response.NotifyTransactionResponse;
import vn.unicloud.umeepay.dtos.request.EncryptedBodyRequest;
import vn.unicloud.umeepay.service.SecurityService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PaygateController extends BaseController implements IPaygateController {

    private final SecurityService securityService;

    @Override
    public ResponseEntity<ResponseBase<InquiryCheckingResponse>> inquiryCheckingSimple(String virtualAccount) {
        InquiryCheckingRequest request = new InquiryCheckingRequest();
        request.setVirtualAccount(virtualAccount);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<DepositCheckingResponse>> depositCheckingSimple(String virtualAccount, Long amount) {
        DepositCheckingRequest request = new DepositCheckingRequest();
        request.setVirtualAccount(virtualAccount);
        request.setAmount(amount);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<NotifyTransactionResponse>> notifyTransactionSimple(@Valid @RequestBody NotifyTransactionRequest request) {
        return this.execute(request);
    }

    /**
     * @param clientId
     * @param signature
     * @param timestamp
     * @param request
     * @return
     */

    @Override
    public ResponseEntity<ResponseBase<String>> inquiryChecking(String clientId, String signature, Long timestamp, EncryptedBodyRequest request) {
        request.setSignature(signature);
        request.setTimestamp(timestamp);
        request.setClientId(clientId);
        InquiryCheckingRequest inquiryCheckingRequest = securityService.paygateAuthenticate(request, InquiryCheckingRequest.class);
        return securityService.encryptPaygateResponse(this.execute(inquiryCheckingRequest));
    }

    @Override
    public ResponseEntity<ResponseBase<String>> depositChecking(String clientId, String signature, Long timestamp, EncryptedBodyRequest request) {
        request.setSignature(signature);
        request.setTimestamp(timestamp);
        request.setClientId(clientId);
        DepositCheckingRequest depositCheckingRequest = securityService.paygateAuthenticate(request, DepositCheckingRequest.class);
        return securityService.encryptPaygateResponse(this.execute(depositCheckingRequest));
    }

    @Override
    public ResponseEntity<ResponseBase<String>> notifyTransaction(String clientId, String signature, Long timestamp, EncryptedBodyRequest request) {
        request.setSignature(signature);
        request.setTimestamp(timestamp);
        request.setClientId(clientId);
        NotifyTransactionRequest notifyTransactionRequest = securityService.paygateAuthenticate(request, NotifyTransactionRequest.class);
        return securityService.encryptPaygateResponse(this.execute(notifyTransactionRequest));
    }

    /**
     * @param clientId
     * @param request
     * @return
     */

    @Override
    public ResponseEntity<ResponseBase<InquiryCheckingClientResponse>> inquiryCheckingClient(String clientId, InquiryCheckingClientRequest request) {
        request.setClientId(clientId);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<DepositCheckingClientResponse>> depositCheckingClient(String clientId, DepositCheckingClientRequest request) {
        request.setClientId(clientId);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<NotifyTransactionClientResponse>> notifyTransactionClient(String clientId, NotifyTransactionClientRequest request) {
        request.setClientId(clientId);
        return this.execute(request);
    }

}
