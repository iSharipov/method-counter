package io.github.isharipov.method.counter.core.counter.type;

import io.micrometer.core.instrument.Tags;

public interface CounterType {

    void count(String metricName, Object[] arguments, Tags tags);
}
