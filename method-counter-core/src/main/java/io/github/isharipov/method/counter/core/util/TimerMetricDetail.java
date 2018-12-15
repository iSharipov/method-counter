package io.github.isharipov.method.counter.core.util;

import io.micrometer.core.instrument.Meter;

public class TimerMetricDetail extends MetricDetail {
    private final double total;
    private final double max;

    public TimerMetricDetail(Meter.Id meter, long count, double total, double max) {
        super(meter, count);
        this.total = total;
        this.max = max;
    }


    public double getTotal() {
        return total;
    }

    public double getMax() {
        return max;
    }
}
