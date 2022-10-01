package com.zwidek.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        rabbitTemplate.convertAndSend("registration", welcomeMessage(request));
        log.info("New customer registration {}", request);
        customerService.registerCustomer(request);
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

