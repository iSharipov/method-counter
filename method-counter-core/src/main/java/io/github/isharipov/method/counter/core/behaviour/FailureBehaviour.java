package io.github.isharipov.method.counter.core.behaviour;

import io.github.isharipov.method.counter.core.AspectInvoker;
import io.github.isharipov.method.counter.core.MetricInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FailureBehaviour implements Behaviour {

    private static final Logger log = LoggerFactory.getLogger(SuccessBehaviour.class);

    @Override
    public Object behave(AspectInvoker aspectInvoker, MetricInvoker metricInvoker) throws Throwable {
        Object result;
        try {
            result = aspectInvoker.proceedWithInvocation();
        } catch (Throwable e) {
            metricInvoker.invoke();
            log.debug("Exception while ProceedingJoinPoint proceed()", e);
            throw e;
        }
        return result;
    }
}
