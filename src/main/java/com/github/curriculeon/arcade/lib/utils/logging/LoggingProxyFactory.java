package com.github.curriculeon.arcade.lib.utils.logging;

import org.apache.commons.lang3.time.StopWatch;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class LoggingProxyFactory<T> {
    public static <E> E createProxy(E target) {
        return new LoggingProxyFactory<>(target).createProxy();
    }

    private final T target; // The target object of type T

    public LoggingProxyFactory(T target) {
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    public T createProxy() {
        // Create a proxy instance that uses an invocation handler to add logging
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new LoggingInvocationHandler(target)
        );
    }

    private class LoggingInvocationHandler implements InvocationHandler, InputOutputSocketInterface {
        private final T target; // The actual object of type T

        public LoggingInvocationHandler(T target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String arguments = "";
            if (args != null) {
                arguments = Arrays.toString(args);
            }
            final StopWatch stopWatch = new StopWatch();
            Object result;

            //Measure method execution time
            String name = method.getName();
            getConsole(AnsiColor.GREEN).println(String.format("Attempting to invoke `%s.%s(%s)`...", target.getClass().getSimpleName(), name, arguments));
            stopWatch.start();
            try {
                result = method.invoke(target, args);
                final long elapsedTime = stopWatch.getTime();
                getConsole(AnsiColor.CYAN).println(String.format("`%s.%s(%s)` resulted in `%s` :: executed in %s ms", target.getClass().getSimpleName(), name, arguments, result, elapsedTime));
                return result;
            } catch (Throwable t) {
                throw t;
            }
        }
    }
}
