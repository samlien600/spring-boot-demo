package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class MyAspect {

    private static Logger log = LoggerFactory.getLogger(MyAspect.class);

    @Around("execution(* com.example.demo.controller.CustomerController.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long spentTime = System.currentTimeMillis() - startTime;
        if (spentTime > 2000L) {
            log.warn(pjp.getSignature().getName() +"() - cost time：" +spentTime +" ms");
        }

        return obj;
    }
}
