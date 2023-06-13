package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculateSpentTime {
    private static Logger log = LoggerFactory.getLogger(CalculateSpentTime.class);

    @Around("execution(public * com.example.demo.controller.CustomerController.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long spentTime = System.currentTimeMillis() - startTime;
        if (spentTime > 2000L) {
            log.warn("{}() - cost time：{} ms", pjp.getSignature().getName(), spentTime);
        }

        return obj;
    }
}
