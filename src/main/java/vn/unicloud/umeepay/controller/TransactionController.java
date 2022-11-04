package vn.unicloud.umeepay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.ITransactionController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.request.PaginationAndSortingRequest;
import vn.unicloud.umeepay.dtos.transaction.request.GetTransactionsRequest;
import vn.unicloud.umeepay.dtos.transaction.response.GetTransactionsResponse;
import vn.unicloud.umeepay.entity.Transaction;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.TransactionStatus;
import vn.unicloud.umeepay.utils.PageUtils;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TransactionController extends BaseController implements ITransactionController {

    @Override
    public ResponseEntity<ResponseBase<GetTransactionsResponse>> getAll(Integer page, Integer size, String keyword, String transactionId, String status, String fromDate, String toDate) {
        try {
            TransactionStatus enumStatus = status != null ? TransactionStatus.valueOf(status) : null;
            GetTransactionsRequest request = GetTransactionsRequest.builder()
                    .pageable(PageUtils.createPageable(new PaginationAndSortingRequest(page, size)))
                    .keyword(keyword)
                    .status(enumStatus)
                    .transactionId(transactionId)
                    .fromDate(fromDate)
                    .toDate(toDate)
                    .build();
            return this.execute(request, GetTransactionsResponse.class);

        } catch (IllegalArgumentException e) {
            List<Transaction> list = new ArrayList<>();
            return ResponseEntity.ok(new ResponseBase<>(new GetTransactionsResponse(PageUtils.convertListToPage(list, PageUtils.createPageable(new PaginationAndSortingRequest(0, size))))));
        }


    }

    @Override
    public ResponseEntity<?> download(Integer page, Integer size, String keyword, String terminalId, String traceId, String branch, String status, String fromDate, String toDate) {
//        try {
//            TransactionStatus statusCode = CommonUtils.reformatStatus(status);
//            List<Transaction> transactions = null;
//            if (TransactionStatus.SUCCESS.equals(statusCode)) {
//                transactions = transactionRepository.findAllBySuccess(
//                    keyword,
//                    traceId,
//                    terminalId,
//                    statusCode,
//                    fromDate == null ? null : LocalDate.parse(fromDate),
//                    toDate == null ? null : LocalDate.parse(toDate),
//                    TransactionStatus.SUCCESS
//                );
//            } else {
//                transactions = transactionRepository.findAllByFail(
//                    keyword,
//                    traceId,
//                    terminalId,
//                    statusCode,
//                    fromDate == null ? null : LocalDate.parse(fromDate),
//                    toDate == null ? null : LocalDate.parse(toDate),
//                    TransactionStatus.SUCCESS
//                );
//            }
//            if (transactions == null || transactions.size() == 0) {
//                return ResponseEntity.ok(new ResponseBase<>(ResponseCode.TRANSACTION_NOT_FOUND.getCode(), ResponseCode.TRANSACTION_NOT_FOUND.getMessage()));
//            }
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            headers.add("content-disposition","attachment;filename=" + "vietqr_transactions_" + LocalDate.now() + ".csv");
//            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//
//            JSONArray jsonArray = new JSONArray(transactions);
//
//            String csvString = CDL.toString(jsonArray);
//
//            return new ResponseEntity<>(csvString.getBytes(StandardCharsets.UTF_8), headers, HttpStatus.OK);
//        } catch (Exception ignored) {}
        return ResponseEntity.ok(new ResponseBase<>(ResponseCode.TRANSACTION_NOT_FOUND.getCode(), ResponseCode.TRANSACTION_NOT_FOUND.getMessage()));
    }
}
