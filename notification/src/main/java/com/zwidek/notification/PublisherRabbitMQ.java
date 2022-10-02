package com.zwidek.notification;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class PublisherRabbitMQ {

    private RabbitTemplate rabbitTemplate;

    @GetMapping("/receiveMessage")
    public String get() {
        Object message = rabbitTemplate.receiveAndConvert("registration");
        return Objects.requireNonNull(message).toString();
    }
}
