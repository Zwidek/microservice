package com.zwidek.customer.controller;

import com.zwidek.customer.model.Customer;
import com.zwidek.customer.model.CustomerRegistrationRequest;
import com.zwidek.customer.repository.CustomerRepository;
import com.zwidek.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        rabbitTemplate.convertAndSend("registration", welcomeMessage(request));
        log.info("New Customer registration {}", request);
        customerService.registerCustomer(request);
    }

    @GetMapping
    public List<Customer> customers() {
        log.info("all Customers");
        return customerRepository.findAll();
    }

    @GetMapping("/{customer}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        return customerService.findCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @RabbitListener(queues = "registration")
//    public void rabbitListener(String message) {
//        System.out.println(message);
//    }

    private StringBuilder welcomeMessage(CustomerRegistrationRequest request) {
        StringBuilder sb = new StringBuilder();
        return sb.append("Hello ")
                .append(request.firstName())
                .append("!\n")
                .append("You address email is: ")
                .append(request.email());
    }
}

