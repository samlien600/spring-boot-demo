package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculateSpentTime {
    private static Logger log = LoggerFactory.getLogger(CalculateSpentTime.class);

    @Around("execution(public * com.example.demo.controller.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long spentTime = System.currentTimeMillis() - startTime;
        if (spentTime > 2000L) {
            log.warn("{}() - cost time：{} ms", pjp.getSignature().getName(), spentTime);
        }

        // log test
        String methodName = pjp.getSignature().getName();
        log.trace("{}() - log test", methodName);
        log.debug("{}() - log test", methodName);
        log.info("{}() - log test", methodName);
        log.warn("{}() - log test", methodName);
        log.error("{}() - log test", methodName);

        return obj;
    }

}
