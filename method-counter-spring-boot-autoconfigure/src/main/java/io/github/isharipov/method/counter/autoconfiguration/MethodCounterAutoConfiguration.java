package io.github.isharipov.method.counter.autoconfiguration;

import io.github.isharipov.method.counter.core.counter.CounterAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class MethodCounterAutoConfiguration {

    @Bean
    public CounterAspect counterAspect(MeterRegistry meterRegistry) {
        CounterAspect counterAspect = Aspects.aspectOf(CounterAspect.class);
        counterAspect.setMeterRegistry(meterRegistry);
        return counterAspect;
    }
}
