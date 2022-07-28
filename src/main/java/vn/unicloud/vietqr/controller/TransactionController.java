package vn.unicloud.vietqr.controller;

import org.json.CDL;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.vietqr.controller.interfaces.ITransactionController;
import vn.unicloud.vietqr.core.BaseController;
import vn.unicloud.vietqr.core.ResponseBase;
import vn.unicloud.vietqr.dtos.transaction.request.GetTransactionsRequest;
import vn.unicloud.vietqr.dtos.transaction.response.GetTransactionsResponse;
import vn.unicloud.vietqr.entity.Transaction;
import vn.unicloud.vietqr.enums.ResponseCode;
import vn.unicloud.vietqr.enums.TransactionStatus;
import vn.unicloud.vietqr.repository.TransactionRepository;
import vn.unicloud.vietqr.utils.CommonUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@RestController
public class TransactionController extends BaseController implements ITransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<ResponseBase<GetTransactionsResponse>> getAll(Integer page, Integer size, String keyword, String terminalId, String traceId, String branch, String status, String fromDate, String toDate) {
        GetTransactionsRequest request = GetTransactionsRequest.builder()
            .pageable(PageRequest.of(page, size))
            .keyword(keyword)
            .status(status)
            .terminalId(terminalId)
            .traceId(traceId)
            .fromDate(fromDate)
            .toDate(toDate)
            .build();

        return this.execute(request, GetTransactionsResponse.class);
    }

    @Override
    public ResponseEntity<?> download(Integer page, Integer size, String keyword, String terminalId, String traceId, String branch, String status, String fromDate, String toDate) {
        try {
            TransactionStatus statusCode = CommonUtils.reformatStatus(status);
            List<Transaction> transactions = null;
            if (TransactionStatus.SUCCESS.equals(statusCode)) {
                transactions = transactionRepository.findAllBySuccess(
                    keyword,
                    traceId,
                    terminalId,
                    statusCode,
                    fromDate == null ? null : LocalDate.parse(fromDate),
                    toDate == null ? null : LocalDate.parse(toDate),
                    TransactionStatus.SUCCESS
                );
            } else {
                transactions = transactionRepository.findAllByFail(
                    keyword,
                    traceId,
                    terminalId,
                    statusCode,
                    fromDate == null ? null : LocalDate.parse(fromDate),
                    toDate == null ? null : LocalDate.parse(toDate),
                    TransactionStatus.SUCCESS
                );
            }
            if (transactions == null || transactions.size() == 0) {
                return ResponseEntity.ok(new ResponseBase<>(ResponseCode.TRANSACTION_NOT_FOUND.getCode(), ResponseCode.TRANSACTION_NOT_FOUND.getMessage()));
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.add("content-disposition","attachment;filename=" + "vietqr_transactions_" + LocalDate.now() + ".csv");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            JSONArray jsonArray = new JSONArray(transactions);

            String csvString = CDL.toString(jsonArray);

            return new ResponseEntity<>(csvString.getBytes(StandardCharsets.UTF_8), headers, HttpStatus.OK);
        } catch (Exception ignored) {}
        return ResponseEntity.ok(new ResponseBase<>(ResponseCode.TRANSACTION_NOT_FOUND.getCode(), ResponseCode.TRANSACTION_NOT_FOUND.getMessage()));
    }
}
