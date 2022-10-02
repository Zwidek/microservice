package com.zwidek.customer.aspekt;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Aspect
@Component
public class LoggInitializer {

    @Pointcut("within(com.zwidek.customer.controller..*)")
    private void restController() {
    }

    @Around("restController()")
    public Object dodajUuid(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            String uuid = UUID.randomUUID().toString();
            MDC.put("uuid", uuid);
            System.out.println("Uuid " + uuid);
            return joinPoint.proceed();
        } finally {
            MDC.remove("uuid");
            System.out.println("Uuid remove");
        }
    }
}
