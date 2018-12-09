package io.github.isharipov.method.counter.core.behaviour;

import io.github.isharipov.method.counter.core.AspectInvoker;
import io.github.isharipov.method.counter.core.MetricInvoker;

public interface Behaviour {
    Object behave(AspectInvoker aspectInvoker, MetricInvoker metricInvoker) throws Throwable;

    enum Type {
        ALWAYS, SUCCESS, FAILURE
    }
}
