package com.zwidek.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        rabbitTemplate.convertAndSend("registration", welcomeMessage(request));
        LOG.info("New Customer registration {}", request);
        customerService.registerCustomer(request);
    }

    @GetMapping
    public List<Customer> customers() {
        LOG.info("all Customers");
        return customerRepository.findAll();
    }

    @RabbitListener(queues = "registration")
    public void rabbitListener(String message) {
        System.out.println(message);
    }

    private StringBuilder welcomeMessage(CustomerRegistrationRequest request) {
        StringBuilder sb = new StringBuilder();
        return sb.append("Hello ")
                .append(request.firstName())
                .append("!\n")
                .append("You address email is: ")
                .append(request.email());
    }
}

