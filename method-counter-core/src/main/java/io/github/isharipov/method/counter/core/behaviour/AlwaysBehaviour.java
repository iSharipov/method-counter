package io.github.isharipov.method.counter.core.behaviour;

import io.github.isharipov.method.counter.core.AspectInvoker;
import io.github.isharipov.method.counter.core.MetricInvoker;

public class AlwaysBehaviour implements Behaviour {

    @Override
    public Object behave(AspectInvoker aspectInvoker, MetricInvoker metricInvoker) throws Throwable {
        try {
            return aspectInvoker.proceedWithInvocation();
        } finally {
            metricInvoker.invoke();
        }
    }
}
