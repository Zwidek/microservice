package com.zwidek.customer.actuator;

import com.zwidek.customer.model.FraudCheckResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class CustomerHealthIndicator implements HealthIndicator {
    private final String DATABASE_SERVICE = "Database Service";

    private final RestTemplate restTemplate;

    @Override
    public Health health() {
        if (isDatabaseGood()) {
            return Health.up().withDetail(DATABASE_SERVICE, "Service is running").build();
        }
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
