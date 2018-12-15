package io.github.isharipov.method.counter.core.counter;

import io.github.isharipov.method.counter.core.counter.behaviour.CounterBehaviourAspectSupport;
import io.github.isharipov.method.counter.core.util.TimerMetricDetail;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * AspectJ for intercepting methods annotated with @Counter.
 */
@Aspect
public class CounterAspect extends CounterBehaviourAspectSupport {

    private static final Logger log = LoggerFactory.getLogger(CounterAspect.class);

    @Around("execution(* *(..)) && @annotation(io.github.isharipov.method.counter.core.counter.Counter)")
    public Object counterMethod(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Counter counter = method.getAnnotation(Counter.class);
        Tags tagsBasedOnJoinPoint = Tags.of("class", pjp.getStaticPart().getSignature().getDeclaringTypeName(),
                "method", pjp.getStaticPart().getSignature().getName());
        if (counter.timer()) {
            Timer.Sample sample = Timer.start(getMeterRegistry());
            try {
                return invokeWithinBehaviour(method, pjp.getArgs(), tagsBasedOnJoinPoint, pjp::proceed);
            } catch (Exception ex) {
                log.debug("Exception while ProceedingJoinPoint proceed()", ex);
                throw ex;
            } finally {
                try {
                    Timer timer = getMeterRegistry().timer("methods.timer", tagsBasedOnJoinPoint);
                    sample.stop(timer);
                    log.debug(new TimerMetricDetail(timer.getId(), timer.count(), timer.totalTime(TimeUnit.SECONDS), timer.max(TimeUnit.SECONDS)).toString());
                } catch (Exception e) {
                    // none
                }
            }
        }
        return invokeWithinBehaviour(method, pjp.getArgs(), tagsBasedOnJoinPoint, pjp::proceed);
    }
}