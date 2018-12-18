package io.github.isharipov.method.counter.core.counter.behaviour;


import io.github.isharipov.method.counter.core.AspectInvoker;
import io.github.isharipov.method.counter.core.MetricInvoker;
import io.github.isharipov.method.counter.core.behaviour.BehaviourProvider;
import io.github.isharipov.method.counter.core.counter.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.lang.reflect.Method;

public abstract class CounterBehaviourAspectSupport {

    private MeterRegistry meterRegistry;

    public CounterBehaviourAspectSupport() {
        meterRegistry = Metrics.globalRegistry.add(new SimpleMeterRegistry());
    }

    protected Object invokeWithinBehaviour(Method method, Object[] args, Tags tags, AspectInvoker aspectInvoker) throws Throwable {
        Counter counter = method.getAnnotation(Counter.class);
        Class behaviour = counter.behaviour();
        tags = tags.and(Tag.of("behaviour", behaviour.getSimpleName().toLowerCase()));

        AspectInvoker aspectAdapter = new AspectBehaviourInvokerAdapter(aspectInvoker);
        MetricInvoker metricAdapter = new MetricInvokerAdapter(counter, args, tags);

        return BehaviourProvider.behaviourProvider.behaviour(counter.behaviour()).behave(aspectAdapter, metricAdapter);
    }

    public void setMeterRegistry(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public MeterRegistry getMeterRegistry() {
        return meterRegistry;
    }

    private Object invoke(AspectInvoker invoker) throws Throwable {
        return invoker.proceedWithInvocation();
    }

    private class AspectBehaviourInvokerAdapter implements AspectInvoker {

        private final AspectInvoker delegate;

        AspectBehaviourInvokerAdapter(AspectInvoker delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object proceedWithInvocation() throws Throwable {
            return invoke(delegate);
        }
    }

    private class MetricInvokerAdapter implements MetricInvoker {

        private final Counter counter;
        private final Object[] args;
        private final Tags tags;

        MetricInvokerAdapter(Counter counter, Object[] args, Tags tags) {
            this.counter = counter;
            this.args = args;
            this.tags = tags;
        }

        @Override
        public void invoke() throws Throwable {
            counter.type()
                    .getDeclaredConstructor(MeterRegistry.class)
                    .newInstance(meterRegistry)
                    .count(counter.name(), args, tags);
        }
    }
}
