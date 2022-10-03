package com.zwidek.notification;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class PublisherRabbitMQ {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PublisherRabbitMQ(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/receiveMessage")
    public String get() {
        Object message = rabbitTemplate.receiveAndConvert("registration");
        return Objects.requireNonNull(message).toString();
    }
}
