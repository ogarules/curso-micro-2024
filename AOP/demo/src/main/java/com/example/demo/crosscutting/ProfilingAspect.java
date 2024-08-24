package com.example.demo.crosscutting;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class ProfilingAspect {
    @Around("execution(* com.example.demo..*.*(..))")
    public Object profileMethods(ProceedingJoinPoint pjp) throws Throwable {

        long initTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long finalTime = System.currentTimeMillis();

        log.info("{} se ejecuto en {} ms", pjp.toShortString(), finalTime - initTime);

        return result;
    }
}
