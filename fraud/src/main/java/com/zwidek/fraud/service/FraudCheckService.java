package com.zwidek.fraud.service;

import com.zwidek.fraud.model.FraudCheckHistory;
import com.zwidek.fraud.repository.FraudCheckHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Integer customerid){
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