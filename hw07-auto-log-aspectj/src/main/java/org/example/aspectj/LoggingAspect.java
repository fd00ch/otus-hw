package org.example.aspectj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.lang.reflect.Array;
import java.util.Arrays;

@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
    @Before("@annotation(LogAspectJ)")
    public void logBefore(JoinPoint joinPoint) {
        String parameters = getParameters(joinPoint.getArgs());
        LOGGER.info("executed method: {}, params: {}", joinPoint.getSignature().getName(), parameters);
    }

    private String getParameters(Object[] args) {
        return Arrays.stream(args)
                .map(obj -> {
                    if (obj.getClass().isArray()) {
                        var length = Array.getLength(obj);
                        var array = new Object[length];
                        for (int i = 0; i < length; i++) {
                            array[i] = Array.get(obj, i);
                        }
                        return Arrays.toString(array);
                    } else {
                        return obj;
                    }
                })
                .map(Object::toString)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }

}
