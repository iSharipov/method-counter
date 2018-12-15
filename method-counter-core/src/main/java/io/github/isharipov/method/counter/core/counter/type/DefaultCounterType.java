package io.github.isharipov.method.counter.core.counter.type;

import io.github.isharipov.method.counter.core.util.MetricDetail;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultCounterType implements CounterType {

    private static final Logger log = LoggerFactory.getLogger(DefaultCounterType.class);

    private static final String DEFAULT_METRIC_NAME = "methods.counter";

    private final MeterRegistry meterRegistry;

    public DefaultCounterType(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public void count(String metricName, Object[] arguments, Tags tags) {
        metricName = metricName.isEmpty() ? DEFAULT_METRIC_NAME : metricName;
        Counter counter = meterRegistry.counter(metricName, tags);
        counter.increment();
        log.debug(new MetricDetail(counter.getId(), (long) counter.count()).toString());
    }
}
