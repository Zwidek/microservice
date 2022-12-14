package com.zwidek.fraud.controller;

import com.zwidek.fraud.model.FraudCheckHistory;
import com.zwidek.fraud.model.FraudCheckResponse;
import com.zwidek.fraud.repository.FraudCheckHistoryRepository;
import com.zwidek.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fraud-check")
@Slf4j
public class FraudController {

    private final FraudCheckService fraudCheckService;
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @GetMapping(path = "/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        LOG.info("Fraud check request for customer {}", customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }

    @GetMapping()
    public List<FraudCheckHistory> fraudCheckHistoryList() {
        return fraudCheckService.fraudCheckHistories();
    }
}
