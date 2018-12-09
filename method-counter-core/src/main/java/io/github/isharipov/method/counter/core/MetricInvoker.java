package io.github.isharipov.method.counter.core;

import java.lang.reflect.InvocationTargetException;

@FunctionalInterface
public interface MetricInvoker {
    void invoke() throws Throwable;
}
