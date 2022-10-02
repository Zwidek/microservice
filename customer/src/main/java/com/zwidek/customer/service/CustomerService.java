package com.zwidek.customer.service;

import com.zwidek.customer.model.Customer;
import com.zwidek.customer.model.CustomerRegistrationRequest;
import com.zwidek.customer.model.FraudCheckResponse;
import com.zwidek.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo check if email valid
        // todo: check if email not taken
        // todo: check if fraudster
        FraudCheckResponse response = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        if (response.isFraudster() || response.equals(null)) {
            throw new IllegalStateException("fraudster");
        }
        customerRepository.saveAndFlush(customer);
    }
}
