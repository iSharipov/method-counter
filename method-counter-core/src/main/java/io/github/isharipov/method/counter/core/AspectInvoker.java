package io.github.isharipov.method.counter.core;

@FunctionalInterface
public interface AspectInvoker {

    Object proceedWithInvocation() throws Throwable;
}
