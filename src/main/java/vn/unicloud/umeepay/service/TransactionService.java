package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.enums.PaymentType;
import vn.unicloud.umeepay.repository.TransactionRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Long getTotalTransaction(Merchant merchant, LocalDate date) {
        try {
            return transactionRepository.getTotalTransaction(merchant.getId(), date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0L;
    }

    public Long getTotalTransactionAmount(Merchant merchant, LocalDate date) {
        try {
            return transactionRepository.getTotalTransactionAmount(merchant.getId(), date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0L;
    }

    public Map<LocalDate, Map<PaymentType, Long>> getTransactionStatistic(Merchant merchant, LocalDate from, LocalDate to) {
        try {
            Map<LocalDate, Map<PaymentType, Long>> result = new TreeMap<>();
            List<PaymentType> allPaymentTypes = Arrays.asList(PaymentType.values());

            from.datesUntil(to.plusDays(1))
                    .collect(Collectors.toList())
                    .stream()
                    .forEach(date -> {
                        result.put(date, allPaymentTypes
                                .stream()
                                .collect(Collectors.toMap(type -> type, type -> 0L)));
                    });

            List<Map<String, Object>> statistics = transactionRepository.getTransactionStatistics(merchant.getId(), from, to);
            Map<LocalDate, Map<PaymentType, Long>> mapResponse = statistics
                    .stream()
                    .collect(Collectors.groupingBy(
                            entry -> ((Date) entry.get("date")).toLocalDate(),
                            Collectors.collectingAndThen(Collectors.toList(), list -> {
                                Map<PaymentType, Long> mapResult = allPaymentTypes
                                        .stream()
                                        .collect(Collectors.toMap(type -> type, type -> 0L));

                                list.stream()
                                        .forEach(entry -> {
                                            mapResult.put(
                                                    PaymentType.valueOf(entry.get("type").toString()),
                                                    Long.parseLong(entry.get("amount").toString())
                                            );
                                        });
                                return mapResult;
                            })));

            mapResponse.entrySet()
                    .stream()
                    .forEach(entry -> result.put(entry.getKey(), entry.getValue()));

            return result;

        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("Get transaction statistic failed {}", ex.getMessage());
        }
        return new HashMap<>();
    }
}
