package com.zwidek.customer.service;

import com.zwidek.customer.model.Customer;
import com.zwidek.customer.model.CustomerRegistrationRequest;
import com.zwidek.customer.model.FraudCheckResponse;
import com.zwidek.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
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
        customerRepository.saveAndFlush(customer);
        // todo: check if fraudster
        log.info("Customer was saved in db {}", customer);
        FraudCheckResponse response = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        if (Objects.requireNonNull(response).isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
    }

    public Optional<Customer> findCustomerById(Integer id) {
        return customerRepository.findById(id);
    }
}
