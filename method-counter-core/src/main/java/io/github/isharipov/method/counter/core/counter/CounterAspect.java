package io.github.isharipov.method.counter.core.counter;

import io.github.isharipov.method.counter.core.counter.behaviour.CounterBehaviourAspectSupport;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * AspectJ for intercepting methods annotated with @Counter.
 */
@Aspect
public class CounterAspect extends CounterBehaviourAspectSupport {

    public CounterAspect() {
        this(Metrics.globalRegistry.add(new SimpleMeterRegistry()));
    }

    public CounterAspect(MeterRegistry meterRegistry) {
        super(meterRegistry);
    }

    @Around("execution(* *(..)) && @annotation(io.github.isharipov.method.counter.core.counter.Counter)")
    public Object counterMethod(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        Tags tagsBasedOnJoinPoint = Tags.of("class", pjp.getStaticPart().getSignature().getDeclaringTypeName(),
                "method", pjp.getStaticPart().getSignature().getName());

        return invokeWithinBehaviour(method, pjp.getArgs(), tagsBasedOnJoinPoint, pjp::proceed);
    }
}