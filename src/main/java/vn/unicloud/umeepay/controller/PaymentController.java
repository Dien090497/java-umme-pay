package vn.unicloud.umeepay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IPaymentController;
import vn.unicloud.umeepay.client.testapi.transaction.request.*;
import vn.unicloud.umeepay.client.testapi.transaction.response.*;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.payment.request.CancelTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.request.CreateTransactionRequest;
import vn.unicloud.umeepay.dtos.request.EncryptedBodyRequest;
import vn.unicloud.umeepay.dtos.payment.request.QueryTransactionRequest;
import vn.unicloud.umeepay.dtos.payment.response.CancelTransactionResponse;
import vn.unicloud.umeepay.dtos.response.EncryptBodyResponse;
import vn.unicloud.umeepay.dtos.payment.response.QueryTransactionResponse;
import vn.unicloud.umeepay.dtos.payment.response.CreateTransactionResponse;
import vn.unicloud.umeepay.service.SecurityService;

@RestController
public class PaymentController extends BaseController implements IPaymentController {

    @Autowired
    private SecurityService securityService;


    @Override
    public ResponseEntity<ResponseBase<EncryptBodyResponse>> createTransaction(String clientId, String signature, Long timestamp, EncryptedBodyRequest request) {
        request.setSignature(signature);
        request.setTimestamp(timestamp);
        request.setClientId(clientId);
        CreateTransactionRequest createTransactionRequest = securityService.authenticate(request, CreateTransactionRequest.class);
        request.setCredential(createTransactionRequest.getCredential());
        return securityService.encryptResponse(request ,this.execute(createTransactionRequest, CreateTransactionResponse.class));
    }

    @Override
    public ResponseEntity<ResponseBase<EncryptBodyResponse>> checkTransaction(String clientId, String signature, Long timestamp, EncryptedBodyRequest request) {
        request.setSignature(signature);
        request.setTimestamp(timestamp);
        request.setClientId(clientId);

        QueryTransactionRequest queryTransactionRequest = securityService.authenticate(request, QueryTransactionRequest.class);
        request.setCredential(queryTransactionRequest.getCredential());
        return securityService.encryptResponse(request ,this.execute(queryTransactionRequest, QueryTransactionResponse.class));

    }

    @Override
    public ResponseEntity<ResponseBase<EncryptBodyResponse>> cancelTransaction(String clientId, String signature, Long timestamp, EncryptedBodyRequest request) {
        request.setSignature(signature);
        request.setTimestamp(timestamp);
        request.setClientId(clientId);


        CancelTransactionRequest cancelTransactionRequest = securityService.authenticate(request, CancelTransactionRequest.class);
        request.setCredential(cancelTransactionRequest.getCredential());
        return securityService.encryptResponse(request ,this.execute(cancelTransactionRequest, CancelTransactionResponse.class));

    }




    /**
     * @Client
     */



    @Override
    public ResponseEntity<ResponseBase<CreateTransactionClientResponse>> createTransactionClient(String clientId, CreateTransactionClientRequest request) {
        request.setClientId(clientId);
        return this.execute(request, CreateTransactionClientResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<QueryTransactionClientResponse>> checkTransactionClient(String clientId, QueryTransactionClientRequest request) {
        request.setClientId(clientId);
        return this.execute(request, QueryTransactionClientResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<CancelTransactionClientResponse>> cancelTransactionClient(String clientId, CancelTransactionClientRequest request) {
        request.setClientId(clientId);
        return this.execute(request, CancelTransactionClientResponse.class);
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
