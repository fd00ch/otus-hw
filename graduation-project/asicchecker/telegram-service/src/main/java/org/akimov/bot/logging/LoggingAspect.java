package org.akimov.bot.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("@annotation(org.akimov.bot.logging.EnableLogging)")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Method called: " + joinPoint.getSignature().getName());
    }

    @Around("@annotation(org.akimov.bot.logging.TrackExecutionTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endTime = System.currentTimeMillis();

        log.info("Class Name: "
                + point.getSignature().getDeclaringTypeName()
                + ". Method Name: "+ point.getSignature().getName()
                + ". Time taken for Execution is : "
                + (endTime - startTime) +"ms");
        return object;
    }

}
