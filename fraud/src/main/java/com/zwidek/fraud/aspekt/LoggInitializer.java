package com.zwidek.fraud.aspekt;

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

    @Pointcut("within(com.zwidek.fraud.controller..*)")
    private void restController() {
    }

    @Around("restController()")
    public Object dodajUuid(ProceedingJoinPoint joinPoint) throws Throwable {
        String uuid = UUID.randomUUID().toString();
        try {
            MDC.put("uuid", uuid);
            return joinPoint.proceed();
        } finally {
            MDC.remove("uuid");
        }
    }
}
