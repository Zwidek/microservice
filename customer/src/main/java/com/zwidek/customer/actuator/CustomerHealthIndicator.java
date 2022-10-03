package com.zwidek.customer.actuator;

import com.zwidek.customer.model.FraudCheckResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Slf4j
@Component
public class CustomerHealthIndicator implements HealthIndicator {
    private final String DATABASE_SERVICE = "Database Service";

    private final RestTemplate restTemplate;

    @Override
    public Health health() {
        log.info("Checking for connection between Customer and Fraud applications");
        if (isDatabaseGood()) {
            log.info("Service is runnig");
            return Health.up().withDetail(DATABASE_SERVICE, "Service is running").build();
        }
        log.error("There is no connection with database");
        return Health.down().withDetail(DATABASE_SERVICE, "Service is not avaliable").build();
    }

    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }

    private boolean isDatabaseGood() {
        ResponseEntity<FraudCheckResponse> response = this.restTemplate.getForEntity("http://FRAUD/api/v1/fraud-check/1", FraudCheckResponse.class);
        return HttpStatus.OK.equals(response.getStatusCode());
    }

}
