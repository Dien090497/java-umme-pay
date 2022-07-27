package vn.unicloud.vietqr.controller;

import org.json.CDL;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.vietqr.controller.interfaces.IAuthController;
import vn.unicloud.vietqr.controller.interfaces.ITransactionController;
import vn.unicloud.vietqr.core.BaseController;
import vn.unicloud.vietqr.core.ResponseBase;
import vn.unicloud.vietqr.dtos.request.ClientLoginRequest;
import vn.unicloud.vietqr.dtos.request.LoginRequest;
import vn.unicloud.vietqr.dtos.response.AccessTokenResponseCustom;
import vn.unicloud.vietqr.dtos.transaction.request.GetTransactionsRequest;
import vn.unicloud.vietqr.dtos.transaction.response.GetTransactionsResponse;
import vn.unicloud.vietqr.entity.Transaction;
import vn.unicloud.vietqr.repository.TransactionRepository;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@RestController
public class TransactionController extends BaseController implements ITransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<ResponseBase<GetTransactionsResponse>> getAll(Integer page, Integer size, String keyword, String branch, String status, String result, String fromDate, String toDate) {
        GetTransactionsRequest request = GetTransactionsRequest.builder()
            .pageable(PageRequest.of(page, size))
            .keyword(keyword)
            .branch(branch)
            .status(status)
            .result(result)
            .fromDate(fromDate)
            .toDate(toDate)
            .build();

        return this.execute(request, GetTransactionsResponse.class);
    }

    @Override
    public ResponseEntity<byte[]> download(Integer page, Integer size, String keyword, String branch, String status, String result, String fromDate, String toDate) {
        try {
            List<Transaction> transactions = transactionRepository.findAllByFilterKeyword(keyword);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.add("content-disposition","attachment;filename=" + "vietqr_transactions_" + LocalDate.now() + ".csv");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            JSONArray jsonArray = new JSONArray(transactions);

            String csvString = CDL.toString(jsonArray);

            return new ResponseEntity<>(csvString.getBytes(StandardCharsets.UTF_8), headers, HttpStatus.OK);
        } catch (Exception ignored) {}
        return ResponseEntity.ok(null);
    }
}
