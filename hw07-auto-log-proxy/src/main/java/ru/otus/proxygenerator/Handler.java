package ru.otus.proxygenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Handler<T> implements InvocationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Handler.class);
    private final T classImplInstance;

    public Handler(T classImplInstance) {
        this.classImplInstance = classImplInstance;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        var classMethod = getClassMethod(classImplInstance.getClass(), method.getName(), args);
        if (classMethod != null && classMethod.isAnnotationPresent(ru.otus.annotations.Log.class)) {
            String parameters = getParameters(args);
            LOGGER.info("executed method: {}, params: {}", method.getName(), parameters);
        }
        return method.invoke(classImplInstance, args);
    }

    private Method getClassMethod(Class<?> clazz, String methodName, Object[] args) {
        try {
            Class<?>[] parameterTypes = Arrays.stream(args)
                    .map(Object::getClass)
                    .toArray(Class<?>[]::new);
            return clazz.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
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
