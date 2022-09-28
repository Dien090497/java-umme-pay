package vn.unicloud.umeepay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IPaymentController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.payment.request.CancelTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.request.CreateTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.request.EncryptedBodyRequest;
import vn.unicloud.umeepay.dtos.payment.request.QueryTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.response.CancelTransactionResponse;
import vn.unicloud.umeepay.dtos.payment.response.QueryTransactionResponse;
import vn.unicloud.umeepay.dtos.payment.response.CreateTransactionResponse;
import vn.unicloud.umeepay.service.SecurityService;

@RestController
public class PaymentController extends BaseController implements IPaymentController {

    @Autowired
    private SecurityService securityService;

    @Override
    public ResponseEntity<ResponseBase<CreateTransactionResponse>> createTransaction(String keyId, String signature, Long timestamp, EncryptedBodyRequest request) {
        request.setSignature(signature);
        request.setTimestamp(timestamp);
        request.setKeyId(keyId);
//        securityService.authenticate(request);
        return null;
    }

    @Override
    public ResponseEntity<ResponseBase<QueryTransactionResponse>> checkTransaction(String keyId, String signature, Long timestamp, EncryptedBodyRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseBase<CancelTransactionResponse>> cancelTransaction(String keyId, String signature, Long timestamp, EncryptedBodyRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseBase<CreateTransactionResponse>> createTransactionSimple(String keyId, String secretKey, CreateTransactionRequest request) {
        request.setKeyId(keyId);
        request.setSecretKey(secretKey);
        securityService.simpleAuthenticate(request);
        return this.execute(request, CreateTransactionResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<QueryTransactionResponse>> checkTransactionSimple(String keyId, String secretKey, QueryTransactionRequest request) {
        request.setKeyId(keyId);
        request.setSecretKey(secretKey);
        securityService.simpleAuthenticate(request);
        return this.execute(request, QueryTransactionResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<CancelTransactionResponse>> cancelTransactionSimple(String keyId, String secretKey, CancelTransactionRequest request) {
        request.setKeyId(keyId);
        request.setSecretKey(secretKey);
        securityService.simpleAuthenticate(request);
        return this.execute(request, CancelTransactionResponse.class);
    }
}
