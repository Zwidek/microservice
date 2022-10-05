package com.zwidek.fraud.service;

import com.zwidek.fraud.model.FraudCheckHistory;
import com.zwidek.fraud.repository.FraudCheckHistoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public List<FraudCheckHistory> fraudCheckHistories() {
        return fraudCheckHistoryRepository.findAll();
    }

    public boolean isFraudulentCustomer(Integer customerid) {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerid)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }

}
