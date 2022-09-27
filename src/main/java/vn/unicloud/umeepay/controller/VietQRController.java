package vn.unicloud.umeepay.controller;

//import org.springframework.http.ResponseEntity;
//import vn.unicloud.umeepay.controller.interfaces.IPaymentController;
//import vn.unicloud.umeepay.core.BaseController;
//import vn.unicloud.umeepay.core.ResponseBase;
//import vn.unicloud.umeepay.dtos.payment.request.CreateTransactionRequest;
//import vn.unicloud.umeepay.dtos.payment.response.CreateTransactionResponse;
//import vn.unicloud.umeepay.dtos.vietqr.request.*;
//import vn.unicloud.umeepay.dtos.vietqr.response.*;

//@RestController
//public class VietQRController extends BaseController implements IPaymentController {
//
//    @Override
//    public ResponseEntity<ResponseBase<CreateTransactionResponse>> createTransaction(CreateTransactionRequest request) {
//        return this.execute(request, CreateTransactionResponse.class);
//    }
//
//    @Override
//    public ResponseEntity<ResponseBase<CheckTransactionResponse>> checkTransaction(String transactionId) {
//        CheckTransactionRequest request = new CheckTransactionRequest();
//        request.setTransactionId(transactionId);
//        return this.execute(request, CheckTransactionResponse.class);
//    }
//
//    @Override
//    public ResponseEntity<ResponseBase<CancelTransactionResponse>> cancelTransaction(String transactionId) {
//        CancelTransactionRequest request = new CancelTransactionRequest();
//        request.setTransactionId(transactionId);
//        return this.execute(request, CancelTransactionResponse.class);
//    }
//
//}
